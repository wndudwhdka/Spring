package com.kh.spring20;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring20.dto.SecurityDto;
import com.kh.spring20.repo.SecurityRepo;

@SpringBootTest
public class Test06Join {

	@Autowired
	private SecurityRepo securityRepo;
	
	@Test
	public void test( ) {
		SecurityDto dto = new SecurityDto();
		dto.setMemberId("khacademy1");
		dto.setMemberPw("teacher1234");
		
		securityRepo.insert(dto);
	}
	
}




