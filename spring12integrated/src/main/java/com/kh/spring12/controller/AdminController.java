package com.kh.spring12.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring12.component.RandomComponent;
import com.kh.spring12.dao.MemberDao;
import com.kh.spring12.dao.MemberStatDao;
import com.kh.spring12.dao.PocketmonStatDao;
import com.kh.spring12.dao.SubjectStatDao;
import com.kh.spring12.dto.MemberDto;
import com.kh.spring12.dto.PocketmonStatDto;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private RandomComponent randomComponent;
	
	@Autowired
	private PocketmonStatDao pocketmonStatDao;
	
	@Autowired
	private SubjectStatDao subjectStatDao;
	
	@Autowired
	private MemberStatDao memberStatDao;
	
	//관리자 홈
	@GetMapping("/home")
	public String home() {
		return "/WEB-INF/views/admin/home.jsp";
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		
		//화면에서 목록 하단에 숫자 링크(페이지 네비게이터)를 보여주려면
		//총 몇 페이지가 있는지를 계산해야 한다
		//총 몇 페이지가 있는지 계산하려면 전체 게시글이 몇 개인지 알아야 한다
		int totalCount = memberDao.selectCount();
		model.addAttribute("totalCount", totalCount);
		
		int totalPage = (totalCount + size-1) / size;
		model.addAttribute("totalPage", totalPage);
		
		List<MemberDto> list = memberDao.selectListPaging(page, size);
		model.addAttribute("list", list);
		return "/WEB-INF/views/admin/member/list.jsp";
	}
	
	
	@GetMapping("/member/detail")
	public String memberDetail(Model model, 
						@RequestParam String memberId) {
		model.addAttribute("memberDto", memberDao.selectOne(memberId));
		return "/WEB-INF/views/admin/member/detail.jsp";
	}
	
	@GetMapping("/member/exit")
	public String memberExit(
			@RequestParam String memberId,
			@RequestParam(required = false, defaultValue = "1") int page,
			RedirectAttributes attr) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		memberDao.delete(memberId);
		memberDao.insertWaiting(memberDto);
		
		attr.addAttribute("page", page);
		return "redirect:list";
	}
	
	@GetMapping("/member/password")
	public String memberPassword(
//			Model model, 
//			RedirectAttributes attr,
			@RequestParam String memberId,
			HttpSession session) {
		String memberPw = randomComponent.generateString(10);
		
		memberDao.changePassword(memberId, memberPw);
//		model.addAttribute("memberPw", memberPw);
//		return "/WEB-INF/views/admin/member/password.jsp";
		
//		비밀번호는 절대로 파라미터로 첨부하면 안됨
//		attr.addAttribute("memberPw", memberPw);
		
//		일시적으로 세션에 보관한 뒤 바로 삭제하는 방식으로 전달
//		- 리다이렉트는 무조건 GET방식으로 처리되므로 POST는 불가능
		session.setAttribute("memberPw", memberPw);
		return "redirect:passwordFinish";
	}
	
	@GetMapping("/member/passwordFinish")
	public String passwordFinish(
//					@RequestParam String memberPw,
					HttpSession session,
					Model model) {
		String memberPw = (String)session.getAttribute("memberPw");
		session.removeAttribute("memberPw");
		model.addAttribute("memberPw", memberPw);
		return "/WEB-INF/views/admin/member/password.jsp";
	}
	
//	회원 정보 변경
	@GetMapping("/member/edit")
	public String memberEdit(@RequestParam String memberId, Model model) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		model.addAttribute("memberDto", memberDto);
		return "/WEB-INF/views/admin/member/edit.jsp";
	}
	
	@PostMapping("/member/edit")
	public String memberEdit(
			@ModelAttribute MemberDto memberDto,
			RedirectAttributes attr) {
		//정보변경
		memberDao.changeInformationByAdmin(memberDto);
		attr.addAttribute("memberId", memberDto.getMemberId());
		return "redirect:detail";
	}
	
	@GetMapping("/stat/pocketmon")
	public String pocketmon(Model model) {
		List<PocketmonStatDto> list = pocketmonStatDao.selectList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/admin/stat/pocketmon.jsp";
	}
	
	@GetMapping("/stat/subject")
	public String subject(Model model) {
		model.addAttribute("list", subjectStatDao.selectList());
		return "/WEB-INF/views/admin/stat/subject.jsp";
	}
	
	@GetMapping("/stat/member")
	public String member(Model model,
			@RequestParam(required = false, 
				defaultValue = "member_level asc") String sort) {
		model.addAttribute("list", memberStatDao.selectList(sort));
		return "/WEB-INF/views/admin/stat/member.jsp";
	}
	
}











