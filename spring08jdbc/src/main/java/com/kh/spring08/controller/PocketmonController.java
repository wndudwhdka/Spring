package com.kh.spring08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring08.dao.PocketmonDao;
import com.kh.spring08.dto.PocketmonDto;

@Controller
public class PocketmonController {
	 
	
	@Autowired
	private PocketmonDao dao; 
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute PocketmonDto dto)
	{
		dao.insert(dto);
		return "등록성공";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String list() {
		List<PocketmonDto> list = dao.selectList();
		
		StringBuffer buffer = new StringBuffer(); 
		for(PocketmonDto dto:list)
		{
			buffer.append(dto.toString()); 
			buffer.append("<br>");
		}
		return buffer.toString();
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public String search(
		@RequestParam String column,
		@RequestParam String keyword)
	{
		List<PocketmonDto> list = dao.selectList(column,keyword);
		
		StringBuffer buffer = new StringBuffer(); 
		for(PocketmonDto dto:list)
		{
			buffer.append(dto.toString());
			buffer.append("<br>"); 
		}
		return buffer.toString();
	}
	
	@RequestMapping("/all-in-one")
	@ResponseBody
	public String allinOne(
			@RequestParam(required=false,defaultValue="name")String column,
			@RequestParam(required=false,defaultValue="")String keyword) 
	{
		boolean search = !keyword.equals(""); 
		
		List<PocketmonDto> list;
		if(search) {
			list = dao.selectList(column,keyword); 
		}
		else {
			list = dao.selectList();
		}
		return list.toString(); 
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(@RequestParam int no) {
		PocketmonDto dto = dao.selectOne(no);
		
		if(dto==null)
		{
			return "없어";
		}
		else {
			return dto.toString(); 
		}
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(@ModelAttribute PocketmonDto dto) {
		 if(dao.update(dto)) return "수정결과는 "+dto.toString()+"입니다.";
		 else return "수정이 이루어지지 않았습니다.";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int no) {
		if(dao.delete(no)) return "도감번호 "+no+"가 삭제 되었습니다.";
		else return "삭제가 온전히 되지 않았습니다.";
	}
}
