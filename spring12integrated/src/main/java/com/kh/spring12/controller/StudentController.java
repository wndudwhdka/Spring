package com.kh.spring12.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring12.dao.StudentDao;
import com.kh.spring12.dto.StudentDto;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDao dao;

	@GetMapping("/insert")
	public String insertInput() {
		
		return "/WEB-INF/views/student/insert.jsp";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute StudentDto studentDto) {
		dao.insert(studentDto);
		return "redirect:/student/insertFinish";
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/student/insertFinish.jsp";
	}
	
}
