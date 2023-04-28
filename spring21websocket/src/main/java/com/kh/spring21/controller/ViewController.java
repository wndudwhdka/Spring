package com.kh.spring21.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		// return "/WEB-INF/views/home.jsp";
		return "home"; 
	}
	
	@PostMapping("/login")
	public String login() {
	
		return "redirect:/view/";
	}
	
}



