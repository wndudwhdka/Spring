package com.kh.spring12.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring12.dao.MemberDao;
import com.kh.spring12.dto.MemberDto;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/join")
	public String join(){
		return "/WEB-INF/views/member/join.jsp";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDto memberDto) {
		memberDao.join(memberDto);
		return "redirect:joinFinish";
	}
	@GetMapping("/joinFinish")
	public String joinFinish() {
		return "/WEB-INF/views/member/joinFinish.jsp"; 
	}
	
	
	
	
	
	@GetMapping("/login")
	public String login() {
		return "/WEB-INF/views/member/login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto userDto,
						RedirectAttributes attr, HttpSession session)
	{
		MemberDto findDto = memberDao.selectOne(userDto.getMemberId());
		//아이디가 트릴ㄹ경우 
		if(findDto ==null)
		{
			attr.addAttribute("mode","error"); 
			return "redirect:login"; 
		}
		// 비밀번호가 틀릴경우
		if(!userDto.getMemberPw().equals(findDto.getMemberPw()))
		{
			attr.addAttribute("mode","error"); 
			return "redirect:login"; 
		}
		// 로그인에 성공한 경우라면 이를 기억하기 위해 HttpSession에 정보를 추가
		// - memberId = 회원아이디
		// - memberLevel = 회원레벨
		session.setAttribute("memberId",findDto.getMemberId());
		session.setAttribute("memberLevel",findDto.getMemberLevel());
		// 최종 로그인 시간 갱신 
		memberDao.updateMemberLogin(findDto.getMemberId());
		
		// 모두 통과한 경우 다시 메인페이지로 
		return "redirect:/";
		
	}
	
	//로그아웃 세션에 저장된 데이터를 삭제하는 작업
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("memberDto"); 
		session.removeAttribute("memberId");
		session.removeAttribute("memberLevel");
		return "redirect:/"; 
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model,HttpSession session) {
		String memberId = (String) session.getAttribute("memberId");
		MemberDto memberDto = memberDao.selectOne(memberId); 
		model.addAttribute("memberDto",memberDto);	
		return "/WEB-INF/views/member/mypage.jsp"; 
	}
	
	
	
}
