package com.kh.spring19;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring19.dto.CertDto;
import com.kh.spring19.repo.CertRepo;

@SpringBootTest
public class CertInsertTest2 {

	@Autowired
	private CertRepo repo;
	
	@Test
	public void test() {
		CertDto certDto = new CertDto();
		certDto.setEmail("testuser@gmail.com");
		certDto.setSecret("123456");
		
		repo.insert(certDto);
	}
	
}
