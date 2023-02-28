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
import com.kh.spring12.dto.MemberStatDto;
import com.kh.spring12.dto.PocketmonStatDto;
import com.kh.spring12.dto.SubjectStatDto;
import com.kh.spring12.vo.MemberPaginationVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private RandomComponent randomComponent;

	@GetMapping("/home")
	public String home() {
		 return "/WEB-INF/views/admin/home.jsp"; 
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model,
			@ModelAttribute ("vo") MemberPaginationVO vo,
			@RequestParam(required=false,defaultValue="1")int page,
			@RequestParam(required=false,defaultValue="10")int size
			) {
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		// 화면에서 목록 하단에 숫자 링크(페이지 네비게이터)를 보여주려면
		// 총 몇 페이지가 있는 지를 계산해야 한다.
		// 총 몇 페이지가 있는 지 계산하려면 전체 게시글이 몇 개인지 알아야 한다. 
		// - select count(*) from member;
		
		int totalCount = memberDao.selectCount();
		model.addAttribute("totalCount", totalCount); 
		vo.setCount(totalCount);
		
		int totalPage = (totalCount + size-1) /10; 
		model.addAttribute("totalPage",totalPage);
		
		
		
		List<MemberDto> list= memberDao.selectListPaging(page, size);
		model.addAttribute("list",list);
		return "/WEB-INF/views/admin/member/list.jsp"; 
	}
	
	@GetMapping("/member/detail")
	public String memberDetail(
			@RequestParam String memberId,
			Model model				
			) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		model.addAttribute("memberDto",memberDto);
		
		
		return "/WEB-INF/views/admin/member/detail.jsp"; 
	}
	
	@GetMapping("/member/exit")
	public String memberexit(
			@RequestParam String memberId,
			@RequestParam(required=false, defaultValue="1") int page,
			RedirectAttributes attr,
			Model model
			)
	{
		// 대기 테이블 추가.
		MemberDto memberDto = memberDao.selectOne(memberId);
		attr.addAttribute("page",page); 
		memberDao.waitTableInsert(memberDto);
		model.addAttribute("memberDto",memberDto);
		
		// 삭제 
		memberDao.exitMember(memberId);
		
		return "redirect:list"; 
	}
	
//	@GetMapping("/member/password")
//	public String password(@RequestParam String memberId,
//			RedirectAttributes attr) 
//	{
//		Random r = new Random();
//		int[] arr = new int[10];  
////		for(int i=0;i<10;i++)
////		{
////			arr[i] = r.nextInt(10); 
////		}
////		
////		String changePw = r.toString();
//		String changePw = "newpassword";
//		memberDao.changePassword(memberId, changePw);
//		return "redirect:list"; 
//	}
	
	@GetMapping("/member/password")
	public String memberpassword(@RequestParam String memberId,
			Model model,
			RedirectAttributes attr,
			HttpSession session
			) 
	{
		String memberPw = randomComponent.generateString(10);
		memberDao.changePassword(memberId, memberPw);
	
		session.setAttribute("memberPw", memberPw); 
		return "redirect:passwordFinish";
	}
	
	@GetMapping("/member/passwordFinish")
	public String passwordFinish(
//				@RequestParam String memberPw,
				HttpSession session,
				Model model 
			) {
		String memberPw = (String)session.getAttribute("memberPw"); 
		session.removeAttribute("memberPw");
		model.addAttribute("memberPw",memberPw);
		return "/WEB-INF/views/admin/member/password.jsp"; 
		
	}
	@GetMapping("/member/edit")
	public String memberEdit(@RequestParam String memberId, Model model)
	{
		MemberDto memberDto = memberDao.selectOne(memberId); 
		model.addAttribute("memberDto",memberDto); 
		return "/WEB-INF/views/admin/member/edit.jsp"; 
	}
	
	@PostMapping("/member/edit")
	public String memberEdit(
			@ModelAttribute MemberDto memberDto,
			RedirectAttributes attr)// 리다이렉트 하면서 데이터를 첨부할 때
	{
		//정보변경
		memberDao.changeInformationByAdmin(memberDto);
		attr.addAttribute("memberId",memberDto.getMemberId());
		return "redirect:detail";
	}
	
	@Autowired
	private PocketmonStatDao pocketmonStatDao;
	
	
	
	@GetMapping("/stat/pocketmon")
	public String pocketmon(Model model) {
		List<PocketmonStatDto> list= pocketmonStatDao.selectList();
		model.addAttribute("list",list); 
		return "/WEB-INF/views/admin/stat/pocketmon.jsp"; 
	}
	
	@Autowired
	private SubjectStatDao subjectStatDao;
	
	@GetMapping("/stat/subject")
	public String subject(Model model) {
		List<SubjectStatDto> list = subjectStatDao.selectList();
		model.addAttribute("list",list);
		return "/WEB-INF/views/admin/stat/subject.jsp"; 
	}
	
	@Autowired
	private MemberStatDao memberStatDao;
	
	@GetMapping("/stat/member")
	public String member(Model model,
			@RequestParam(required = false,
			defaultValue = "member_level asc") String sort)
			 {
		List<MemberStatDto> list = memberStatDao.selectList(sort);
		model.addAttribute("list",list);
		return "/WEB-INF/views/admin/stat/member.jsp"; 
	}

	
	
}
