package com.kh.spring17;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring17.dto.SubjectDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
//		Map<String, Object> param = new HashMap<>();
//		param.put("no", 2);
//		param.put("name", "바꾼과정");
//		param.put("period", 30);
//		param.put("price", 50000);
//		param.put("type", "혼합");
//		sqlSession.update("subject.fix", param);
		
		SubjectDto dto = new SubjectDto();
		dto.setNo(2);
		dto.setName("바꾼과정");
		dto.setPeriod(30);
		dto.setPrice(50000);
		dto.setType("혼합");
		int result = sqlSession.update("subject.fix", dto);
		log.debug("result = {}", result);
	}
	
}
