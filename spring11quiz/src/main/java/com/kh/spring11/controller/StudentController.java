package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring11.dao.StudentDao;
import com.kh.spring11.dto.StudentDto;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute StudentDto studentDto) {
		studentDao.insert(studentDto);
		return "등록 완료";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(
			@RequestParam(required = false, defaultValue = "") String column,
			@RequestParam(required = false, defaultValue = "") String keyword
	) {
		//이번에는 column과 keyword 모두 빈칸이 아닌 경우 검색하는 것으로 처리
		//defaultValue 때문에 절대로 column과 keyword는 null이 나올 수 없음
		boolean search = !column.equals("") && !keyword.equals("");
		
		List<StudentDto> list;
		if(search) {
			list = studentDao.selectList(column, keyword);
		}
		else {
			list = studentDao.selectList();
		}
		
		StringBuffer buffer = new StringBuffer();
		for(StudentDto studentDto : list) {
			buffer.append(studentDto.toString());
			buffer.append("<br>");
		}
		return buffer.toString();
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(@RequestParam int no) {
		StudentDto studentDto = studentDao.selectOne(no);
		return studentDto == null ? "대상 없음" : studentDto.toString();
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(@ModelAttribute StudentDto studentDto) {
		boolean success = studentDao.update(studentDto);
		return success ? "변경 완료" : "대상 없음";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int no) {
		boolean success = studentDao.delete(no);
		return success ? "삭제 완료" : "대상 없음";
	}
}
