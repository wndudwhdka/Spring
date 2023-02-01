package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring11.dao.SubjectDao;
import com.kh.spring11.dto.SubjectDto;

@Controller
@RequestMapping("/subject") 
public class SubjectController {
	
	@Autowired
	private SubjectDao subjectDao;
	
//	@RequestMapping("/insert")
//	@ResponseBody
//	public String insert(@ModelAttribute SubjectDto dto) {
//		subjectDao.insert(dto);
//		return "등록 완료";
//	}
	
//	등록이 완료된 이후에 텍스트 메세지가 아니라 다른 곳으로 보내고 싶다면
//	사용자에게 재접속을 요청하도록 처리할 수 있다(Redirect, 리다이렉트)
//	- @ResponseBody를 제거하고 redirect: 로 시작하는 문자열을 반환한다
	@RequestMapping("/insert")
	public String insert(@ModelAttribute SubjectDto dto) {
		subjectDao.insert(dto);
		return "redirect:list";//상대경로
//		return "redirect:/subject/list";//절대경로
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(
			@RequestParam(required = false, defaultValue = "name") String column,
			@RequestParam(required = false, defaultValue = "") String keyword) {
		List<SubjectDto> list;
		if(keyword.equals("")) {//목록이라면
			list = subjectDao.selectList();
		}
		else {//검색이라면
			list = subjectDao.selectList(column, keyword);
		}
		
		StringBuffer buffer = new StringBuffer();
		for(SubjectDto dto : list) {
			buffer.append(dto.toString());
			buffer.append("<br>");
		}
		return buffer.toString();
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(@RequestParam int no) {
		SubjectDto dto = subjectDao.selectOne(no);
		if(dto == null) {
			return "대상 없음";
		}
		else {
			return dto.toString();
		}
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(@ModelAttribute SubjectDto dto) {
		boolean success = subjectDao.update(dto);
		
		return success ? "변경 성공" : "없는 대상";
//		if(success) {
//			return "변경 성공";
//		}
//		else {
//			return "없는 대상";
//		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int no) {
		boolean success = subjectDao.delete(no);
		return success ? "삭제 완료" : "존재하지 않는 대상";
	}

}
