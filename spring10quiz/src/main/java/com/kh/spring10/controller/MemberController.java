package com.kh.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring10.dao.MemberDao;
import com.kh.spring10.dto.MemberDto;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberDao dao = new MemberDao();
	
	@RequestMapping("/join")
	@ResponseBody
	public String join(@ModelAttribute MemberDto dto) {
		dao.insert(dto); 
		return "회원 가입에 성공했습니다"+"데이터는 다음과 같습니다"+dto.toString(); 
	}
	
}
