package com.kh.spring12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring12.dao.PocketmonDao;
import com.kh.spring12.dto.PocketmonDto;

@Controller
@RequestMapping("/pocketmon")
public class PocketmonController {
	
	@Autowired
	private PocketmonDao pocketmonDao;
	
	@RequestMapping("/insertInput")
	public String insertInput() {
		return "/WEB-INF/views/pocketmon/insertInput.jsp";
	}
	
	@PostMapping("/insertProcess")
	public String insertProcess(@ModelAttribute PocketmonDto pocketmonDto) {
		pocketmonDao.insert(pocketmonDto);
		//return "redirect:/pocketmon/insertFinish";//절대경로
		return "redirect:insertFinish";//상대경로
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/pocketmon/insertFinish.jsp";
	}
	
}