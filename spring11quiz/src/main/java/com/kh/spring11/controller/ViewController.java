package com.kh.spring11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

	@RequestMapping("/subject")
	public String subject() {
		return "/WEB-INF/views/subject.jsp";
	}

	@RequestMapping("/student")
	public String student() {
		return "/WEB-INF/views/student.jsp";
	}
	

}
