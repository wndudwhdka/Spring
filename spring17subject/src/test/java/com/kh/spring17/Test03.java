package com.kh.spring17;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring17.dto.SubjectDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test03 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		SubjectDto dto = new SubjectDto();
		dto.setName("마이바티스 초급과정");
		dto.setPeriod(30);
		dto.setPrice(100000);
		dto.setType("오프라인");
		sqlSession.insert("subject.submit", dto);
	}
	
}





