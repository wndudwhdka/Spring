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
//이 부분은 무조건 @RequestMapping으로 작성
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	private SubjectDao subjectDao;
	
	//TIP : 처리방식이 다르면 주소가 같아도 된다(메소드 오버로딩과 비슷)
	@GetMapping("/insert")
	public String insertInput() {
		return "/WEB-INF/views/subject/insert.jsp";
	}
	
	@PostMapping("/insert")
	public String insertProcess(@ModelAttribute SubjectDto subjectDto) {
		subjectDao.insert(subjectDto);
		return "redirect:insertFinish";//상대
//		return "redirect:/subject/insertFinish";//절대
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/subject/insertFinish.jsp";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam int no) {
		SubjectDto subjectDto = subjectDao.selectOne(no);
		model.addAttribute("subjectDto", subjectDto);
		return "/WEB-INF/views/subject/detail.jsp";
	}
	
	@GetMapping("/list")
	public String list(Model model,
			@RequestParam(required = false, defaultValue = "") String column,
			@RequestParam(required = false, defaultValue = "") String keyword) {
		boolean search = !column.equals("") && !keyword.equals("");
		if(search) {
			model.addAttribute("keyword", keyword);
			model.addAttribute("mode", "검색");
			model.addAttribute("list", subjectDao.selectList(column, keyword));
		}
		else {
			model.addAttribute("mode", "목록");
			model.addAttribute("list", subjectDao.selectList());
		}
		return "/WEB-INF/views/subject/list.jsp";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int no) { 
		subjectDao.delete(no);
		return "redirect:list";
	}
	
}










