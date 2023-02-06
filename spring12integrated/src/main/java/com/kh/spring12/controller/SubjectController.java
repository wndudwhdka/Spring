package com.kh.spring12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/detail")
	public String detial(Model model,@RequestParam int no) {
		SubjectDto dto = subjectDao.selectOne(no);
		model.addAttribute("dto",dto);
		return "/WEB-INF/views/subject/detail.jsp"; 
	}
	
	@GetMapping("/list")
	public String list(Model model,
			@RequestParam(required = false,defaultValue="name")String column,
			@RequestParam(required = false,defaultValue="") String keyword
			) {
		if(keyword.equals(""))
		{
			model.addAttribute("list",subjectDao.selectList());
		}
		else{
			model.addAttribute("keyword",keyword);
			model.addAttribute("list",subjectDao.selectList(column, keyword));
		}
		return "/WEB-INF/views/subject/list.jsp"; 
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int no)
	{
		subjectDao.delete(no); 
		return "redirect:list"; 
	}
}
