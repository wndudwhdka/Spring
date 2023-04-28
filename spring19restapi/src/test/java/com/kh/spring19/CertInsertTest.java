package com.kh.spring19;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring19.dto.CertDto;

@SpringBootTest
public class CertInsertTest {
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		CertDto certDto = new CertDto();
		certDto.setEmail("testuser@gmail.com");
		certDto.setSecret("456987");
		
		sqlSession.insert("cert.add", certDto);
	}
	
}
