package com.kh.spring12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.kh.spring12.dao.SubjectDao;
import com.kh.spring12.dto.SubjectDto;

@Controller
@RequestMapping("/subject") 
public class SubjectController {
	
	@Autowired
	private SubjectDao subjectDao; 
	
	@GetMapping("/insert")
	public String insertInput() {
		return "/WEB-INF/views/subject/insert.jsp";
	}
	
	@PostMapping("/insert")
	public String insertProcess(@ModelAttribute SubjectDto subjectDto) {
		subjectDao.insert(subjectDto);
		//return "redirect:/pocketmon/insertFinish";//절대경로 현재 컨트롤러가 
		return "redirect:insertFinish";//상대경로 현재 insertProcess또한 subject에 있기때문에 가능하다. 
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/subject/insertFinish.jsp";
	}
	
}
