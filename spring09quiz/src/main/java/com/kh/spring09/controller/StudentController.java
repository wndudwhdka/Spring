package com.kh.spring09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring09.dao.StudentDao;
import com.kh.spring09.dto.StudentDto;

@Controller
public class StudentController {

	@Autowired
	private StudentDao dao; 
	
	@RequestMapping("/student/insert")
	@ResponseBody
	public String insert(@ModelAttribute StudentDto dto)
	{
		dao.insert(dto);
		return "입력된 값은 다음과 같습니다 : "+dto.toString();
	}
	
	@RequestMapping("/student/list")
	@ResponseBody
	public String list(
			@RequestParam(required = false,defaultValue="name") String column,
			@RequestParam(required = false,defaultValue="") String keyword)
	{
		List<StudentDto> list; 
		if(keyword.equals(""))
		{
			list = dao.selectList();
			
		}
		else
		{
			list = dao.selectList(column, keyword);
		}
		return list.toString();
	}
	
	@RequestMapping("/student/detail")
	@ResponseBody
	public String detail(@RequestParam int no)
	{
		return no+"번의 자료는 다음과 같습니다."+dao.selectOne(no).toString(); 
	}
	
	@RequestMapping("/student/edit")
	@ResponseBody
	public String edit(@ModelAttribute StudentDto dto)
	{
		if (dao.update(dto)) return "수정이 완료되었습니다.";
		else return "수정이 제대로 되지 않았습니다.";
	}
	
	@RequestMapping("/student/delete")
	@ResponseBody
	public String delete(@RequestParam int no)
	{
		if (dao.delete(no)) return "삭제가 제대로 되었습니다."; 
		else return "삭제가 제대로 되지 않았습니다.";
	}
}
