package com.kh.spring21.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring21.dto.MemberDto;
import com.kh.spring21.repo.MemberRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
	
	@GetMapping("/basic")
	public String basic() {
		//return "/WEB-INF/views/basic.jsp";
		return "basic";
	}
	
	@GetMapping("/chat")
	public String chat() {
		//return "/WEB-INF/views/chat.jsp";
		return "chat";
	}
	
	@GetMapping("/sockjs")
	public String sockjs() {
		//return "/WEB-INF/views/sockjs.jsp";
		return "sockjs";
	}
	
	@GetMapping("/json")
	public String json() {
		//return "/WEB-INF/views/json.jsp";
		return "json";
	}
	
	@GetMapping("/")
	public String home() {
		//return "/WEB-INF/views/home.jsp";
		return "home";
	}
	
	@Autowired
	private MemberRepo memberRepo;
	
	@PostMapping("/login")
	public String login(
				@ModelAttribute MemberDto memberDto,//사용자가 입력한 정보
				HttpSession session
			) {
		MemberDto findDto = memberRepo.login(memberDto);
		if(findDto != null) {//로그인 성공
			session.setAttribute("memberId", findDto.getMemberId());
			session.setAttribute("memberLevel", findDto.getMemberLevel());
		}
		return "redirect:/view/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("memberId");
		session.removeAttribute("memberLevel");
		return "redirect:/view/";
	}
	
	@GetMapping("/member")
	public String member() {
		//return "/WEB-INF/views/member.jsp";
		return "member";
	}
	
	@GetMapping("/vuemember")
	public String vuemember() {
		return "vuemember";
	}
	
	@GetMapping("/channel1")
	public String channel1() {
		//return "/WEB-INF/views/channel1.jsp";
		return "channel1";
	}
	
	@GetMapping("/channel2")
	public String channel2() {
		//return "/WEB-INF/views/channel2.jsp";
		return "channel2";
	}
	
	@GetMapping("/channel3")
	public String channel3() {
		//return "/WEB-INF/views/channel3.jsp";
		return "channel3";
	}
	
	@GetMapping("/channel4")
	public String channel4() {
		//return "/WEB-INF/views/channel4.jsp";
		return "channel4";
	}
	
	@GetMapping("/channel5")
	public String channel5() {
		//return "/WEB-INF/views/channel5.jsp";
		return "channel5";
	}
	
	@GetMapping("/channel6")
	public String channel6() {
		//return "/WEB-INF/views/channel6.jsp";
		return "channel6";
	}
	
}