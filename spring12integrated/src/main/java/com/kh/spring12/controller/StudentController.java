package com.kh.spring12.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/detail")
	public String detail(Model model,
			@RequestParam int no) {
		StudentDto dto = dao.selectOne(no);
		model.addAttribute("dto", dto);
		return "/WEB-INF/views/student/detail.jsp"; 
	}
	
	@GetMapping("/list")
	public String list(Model model,
			@RequestParam(required=false,defaultValue="name") String column,
			@RequestParam(required=false,defaultValue="") String keyword
			)
	{
		if(keyword.equals(""))
		{
			model.addAttribute("list",dao.selectList()); 
		}
		else {
			model.addAttribute("keyword", keyword);
			model.addAttribute("list", dao.selectList(column, keyword));
		}
		
				return "/WEB-INF/views/student/list.jsp"; 
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int no,
			@RequestParam(required = false, defaultValue="") String keyword)
	{
		dao.delete(no);
		if(keyword.equals("")) {
			return "redirect:list";
		}
		return "redirect:list?keyword="+keyword; 
	}
}
