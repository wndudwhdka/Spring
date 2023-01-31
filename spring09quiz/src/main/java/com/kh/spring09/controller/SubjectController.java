package com.kh.spring09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring09.dao.SubjectDao;
import com.kh.spring09.dto.SubjectDto;

@Controller
@RequestMapping("/subject") 
public class SubjectController {

	@Autowired
	private SubjectDao dao; 
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute SubjectDto dto)
	{
		dao.insert(dto);
		return "redirect:list";
//		return "입력된 값은 다음과 같습니다.\n"+dto.toString();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(
			@RequestParam(required=false,defaultValue="name")String column,
			@RequestParam(required=false,defaultValue="")String keyword
	)
	{
		List<SubjectDto> list; 
		if(keyword.equals(""))
		{
			list = dao.selectList();
		}
		else
		{
			list =dao.selectList(column,keyword);
		}
		return "결과는 다음과 같습니다."
				+ "\n"+list.toString();
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(@RequestParam int no)
	{
		return "입력결과는 다음과 같습니다.\n"+dao.selectOne(no); 
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(@ModelAttribute SubjectDto dto)
	{
		if (dao.update(dto)) return "수정완료";
		else return "수정실패"; 
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam int no)
	{
		if (dao.delete(no)) return "삭제완료";
		else return "삭제실패"; 
	}
	
}
