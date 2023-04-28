package com.kh.spring12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring12.dao.MemberDao;

//회원과 관련된 비동기 처리
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RestController
@RequestMapping("/rest/member")
public class MemberRestController {
	
	@Autowired
	private MemberDao memberDao;
	
	//사용가능하면(없으면) Y
	//사용불가하면(있으면) N
	@GetMapping("/memberId/{memberId}")
	public String findId(@PathVariable String memberId) {
		return memberDao.selectOne(memberId) == null ? "Y":"N";
	}
	
	//사용가능하면(없으면) Y
	//사용불가하면(있으면) N
	@GetMapping("/memberNick/{memberNick}")
	public String findNickname(@PathVariable String memberNick) {
		return memberDao.selectByNickname(memberNick) == null ? "Y":"N";
	}
	
}






