package com.kh.spring16;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test07 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//count(*) 구문도 결과가 한 개이므로 selectOne으로 호출
		int count = sqlSession.selectOne("pocketmon.countAll");
		log.debug("count = {}", count);
	}
	
}


