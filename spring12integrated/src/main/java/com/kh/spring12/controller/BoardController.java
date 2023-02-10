package com.kh.spring12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring12.dao.BoardDao;
import com.kh.spring12.dto.BoardDto;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@GetMapping("/home")
	public String home(){
		return "/WEB-INF/views/board/home.jsp"; 
	}
	
	@GetMapping("/list")		// 게시판에서 글목록을 보여준다. 글목록 별로 하이퍼링크 표시 
	public String boardList(Model model, 
			@RequestParam(required=false,defaultValue="1")int page,
			@RequestParam(required=false,defaultValue="3")int size
			) {
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		
		int totalCount = boardDao.selectCount();
		model.addAttribute("totalCount",totalCount); 
		
		int totalPage = (totalCount + size-1) / 10;
		model.addAttribute("totalPage",totalPage); 
		
		List<BoardDto> list = boardDao.selectListPaging(page, size);
		
		model.addAttribute("list",list); 
		return "/WEB-INF/views/board/list.jsp"; 
	}
	
	@GetMapping("/detail") // 글 확인
	public String detail(
			Model model, 
			@RequestParam int no) {
		model.addAttribute(boardDao.selectOne(no)); 
				
		return "/WEB-INF/views/board/detail.jsp"; 
	}
	
	@GetMapping("/delete") // 글 삭제 
	public String delete(
			Model model, 
			@RequestParam int no) {
		boardDao.delete(no); 
		
		return "redirect:/board/list"; 
	}
	
	
	@GetMapping("/write")
	public String write(
			@RequestParam String title,
			@RequestParam String content
			) {
		return "/WEB-INF/views/board/write.jsp"; 
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "/WEB-INF/views/board/edit.jsp"; 
	}
	
	
	
	
}
 