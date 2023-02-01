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
	private StudentDao dao;

	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute StudentDto studentDto) {
		dao.insert(studentDto);
		return "등록 완료";
	}

	@RequestMapping("/list")
	@ResponseBody
	public String list(){
			List<StudentDto> list = dao.selectList();
			
			StringBuffer buffer = new StringBuffer();
			for(StudentDto dto : list) {
				buffer.append(dto.toString());
				buffer.append("<br>");//줄바꿈
			}
			return buffer.toString();
	}

	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam String column, @RequestParam String keyword) {
		List<StudentDto> list = dao.selectList(column, keyword);

		StringBuffer buffer = new StringBuffer();
		for (StudentDto dto : list) {
			buffer.append(dto.toString());
			buffer.append("<br>");// 줄바꿈
		}
		return buffer.toString();
	}
	
	
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(@RequestParam int no) {
		StudentDto studentDto = dao.selectOne(no);
		return studentDto == null ? "대상 없음" : studentDto.toString();
	}

	@RequestMapping("/edit")
	@ResponseBody
	public String edit(@ModelAttribute StudentDto studentDto) {
		boolean success = dao.update(studentDto);
		return success ? "변경 완료" : "대상 없음";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int no) {
		boolean success = dao.delete(no);
		return success ? "삭제 완료" : "대상 없음";
	}
}
