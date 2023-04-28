package com.kh.spring16;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test05 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		int no = 1;
		int result = sqlSession.delete("pocketmon.remove", no);
		log.debug("result = {}", result);
	}
	
}
