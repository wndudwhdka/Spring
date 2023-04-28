package com.kh.spring12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring12.dao.StudentDao;
import com.kh.spring12.dto.StudentDto;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("/insert")
	public String insert() {
		return "/WEB-INF/views/student/insert.jsp";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute StudentDto studentDto) {
		studentDao.insert(studentDto);
		return "redirect:insertFinish";//상대경로
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/student/insertFinish.jsp";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam int no) {
		model.addAttribute("studentDto", studentDao.selectOne(no));
		return "/WEB-INF/views/student/detail.jsp";
	}
	
	@GetMapping("/list")
	public String list(Model model,
			@RequestParam(required = false, defaultValue = "name") String column,
			@RequestParam(required = false, defaultValue = "") String keyword) {
		if(keyword.equals("")) {//목록
			model.addAttribute("list", studentDao.selectList());
		}
		else {//검색
			model.addAttribute("column", column);
			model.addAttribute("keyword", keyword);
			model.addAttribute("list", studentDao.selectList(column, keyword));
		}
		return "/WEB-INF/views/student/list.jsp";
	}
	
	//리다이렉트 사용 시 데이터를 첨부해야 하는 경우가 있다
	//- 스프링에서 RedirectAttributes 라는 도구를 제공한다
	//- Model과 사용법이 동일하다
	@GetMapping("/delete")
	public String delete(@RequestParam int no,
		@RequestParam(required = false, defaultValue = "") String keyword,
		RedirectAttributes attr) {
		studentDao.delete(no);
		if(keyword.equals("")) {
			return "redirect:list";
		}
		else {
			attr.addAttribute("keyword", keyword);
			return "redirect:list";
		}
	}
	
}






