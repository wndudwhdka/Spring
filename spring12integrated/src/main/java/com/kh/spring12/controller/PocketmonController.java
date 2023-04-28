package com.kh.spring12.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring12.dao.PocketmonDao;
import com.kh.spring12.dao.PocketmonWithImageDao;
import com.kh.spring12.dto.PocketmonDto;
import com.kh.spring12.dto.PocketmonWithImageDto;
import com.kh.spring12.service.PocketmonService;

@Controller
@RequestMapping("/pocketmon")
public class PocketmonController {
	
	@Autowired
	private PocketmonDao pocketmonDao;
	
	@Autowired
	private PocketmonService pocketmonService;
	
	@Autowired
	private PocketmonWithImageDao pocketmonWithImageDao;
	
//	등록
//	@RequestMapping("/insertInput")//GET, POST 관계없이 모든 방식 처리
//	@RequestMapping(value = "/insertInput", method = RequestMethod.GET)
	@GetMapping("/insertInput")//GET 방식만 처리
	public String insertInput() {
		return "/WEB-INF/views/pocketmon/insertInput.jsp";
	}
	
//	@PostMapping("/insertProcess")//POST 방식만 처리
//	public String insertProcess(@ModelAttribute PocketmonDto pocketmonDto) {
//		pocketmonDao.insert(pocketmonDto);
//		//return "redirect:/pocketmon/insertFinish";//절대경로
//		return "redirect:insertFinish";//상대경로
//	}
	
	@PostMapping("/insertProcess")//파일업로드 처리
	public String insertProcess(@ModelAttribute PocketmonDto pocketmonDto,
			@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		pocketmonService.insert(pocketmonDto, attach);
		return "redirect:insertFinish";//상대경로
	}
	
	@GetMapping("/insertFinish")
	public String insertFinish() {
		return "/WEB-INF/views/pocketmon/insertFinish.jsp";
	}
	
//	상세
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam int no) {
		PocketmonWithImageDto pocketmonDto = pocketmonWithImageDao.selectOne(no);
		model.addAttribute("pocketmonDto", pocketmonDto);
		return "/WEB-INF/views/pocketmon/detail.jsp";
	}
	
//	목록
	@GetMapping("/list")
	public String list(Model model,
		@RequestParam(required = false, defaultValue = "name") String column,
		@RequestParam(required = false, defaultValue = "") String keyword) {
		if(keyword.equals("")) {//keyword가 비어 있다면
			model.addAttribute("list", pocketmonWithImageDao.selectList());
		}
		else {
			model.addAttribute("list", pocketmonWithImageDao.selectList(column, keyword));
		}
		
		return "/WEB-INF/views/pocketmon/list.jsp";
	}
	
//	삭제
	@GetMapping("/delete")
	public String delete(@RequestParam int no) {
		pocketmonDao.delete(no);
		return "redirect:list";//상대경로
//		return "redirect:/pocketmon/list";//절대경로
	}
	
//	수정(입력+처리)
//	- 수정 입력 페이지는 반드시 모든 기존 정보가 작성이 되어 있도록 구현
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam int no) {
		PocketmonDto pocketmonDto = pocketmonDao.selectOne(no);
		model.addAttribute("pocketmonDto", pocketmonDto);
		return "/WEB-INF/views/pocketmon/edit.jsp";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute PocketmonDto pocketmonDto,
			RedirectAttributes attr) {
		pocketmonDao.update(pocketmonDto);
		
		//redirect에 필요한 no 데이터를 추가 (주소 뒤에 ?no=xxx 가 추가됨)
		attr.addAttribute("no", pocketmonDto.getNo());
		return "redirect:detail";		
	}
}



