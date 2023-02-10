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
	
	
	//비밀번호 변경 기능
	@GetMapping("/password")
	public String password() {
		return "/WEB-INF/views/member/password.jsp"; 
	}
	
	@PostMapping("/password")
	public String password(
			HttpSession session, // 아이디가 저장되어 있는 세션 개체
			@RequestParam String currentPw, //현재 비밀번호
			@RequestParam String changePw, // 변경할 비밀번호 
			RedirectAttributes attr // 리다이렉트에 정보를 추가하기 위한 객체
			) {
		String memberId = (String)session.getAttribute("memeberId");
		MemberDto memberDto = memberDao.selectOne(memberId);
		//비밀먼호가 일치하지 않는다면
		if(!memberDto.getMemberPw().equals(currentPw)) 
		{
			attr.addAttribute("mode","error"); 
			return "redirect:password"; 
		}
		
		// 비밀번호가 일치한다면 비밀번호 변경처리 
		memberDao.changePassword(memberId,changePw);
		return "redirect:passwordFinish.jsp";
	}
	
	@GetMapping("/passwordFinish")
	public String passwordFinish() {
		return "/WEB-INF/views/member/passwordFinish.jsp"; 
	}
	
	@GetMapping("/edit")
	public String edit(
					Model model,  // 회원정보를 JSP로 전달할 전달 객체
					HttpSession session) { // 아이디 저장 세선 객체
		String memberId=(String) session.getAttribute("memberId");
		MemberDto memberDto = memberDao.selectOne(memberId);
		model.addAttribute("memberDto", memberDto);
		return "/WEB-INF/views/member/edit.jsp"; 
	}
	
	@PostMapping("/edit")
	public String edit(
			@ModelAttribute MemberDto memberDto,
			HttpSession session,
			RedirectAttributes attr) {
		String memberId = (String)session.getAttribute("memberId"); 
		MemberDto findDto = memberDao.selectOne(memberId); 
		
		
		// 비밀번호가 일치하지 않는다면 - > 에러 표시 후 이전 ㅍ이지로 리다이레그
		if(!findDto.getMemberPw().equals(memberDto.getMemberPw()))
		{
			attr.addAttribute("mode","error");
			return "redirect:edit"; 
		}
		
		// 비밀번호가 일치하면
		memberDto.setMemberId(memberId);
		memberDao.changeInformation(memberDto);
		return "redirect:editFinish"; 
	}
	@GetMapping("/editFinish")
	public String editFinish() {
		return "/WEB-INF/views/member/editFinish.jsp"; 
	}
	
	
	
	
	@GetMapping("/exit")
	public String exit(
	){
		return "/WEB-INF/views/member/exit.jsp"; 
	}
	
	@PostMapping("/exit")
	public String exit(
			@RequestParam String memberPw,
			HttpSession session,
			RedirectAttributes attr
			) {
		String memberId = (String)session.getAttribute("memberId");
		MemberDto deleteDto = memberDao.selectOne(memberId);
		
		if(!deleteDto.getMemberPw().equals(memberPw))
		{
			attr.addAttribute("mode","error");
			return "redirect:exit"; 
		}
		// 비밀번호가 일치하면 -> 회원탈퇴 + 로그아웃 
		memberDao.exitMember(memberId);
		
		session.removeAttribute("memberId");
		session.removeAttribute("memberLevel"); 
		return "redirect:exitFinish"; 
	}
	
	@GetMapping("/exitFinish")
	public String exitFinish(){
		return "/WEB-INF/views/member/exitFinish.jsp"; 
	}
	
	@GetMapping("/find")
	public String find() {
		return "/WEB-INF/views/member/find.jsp"; 		 
	}
	@PostMapping("/find")
	public String find(
			@ModelAttribute MemberDto memberDto,
			Model model){

		String memberId = memberDao.findId(memberDto);
		model.addAttribute("memberId",memberId); 
		
		return "/WEB-INF/views/member/findresult.jsp"; 
		
	}
	
	
	
}


