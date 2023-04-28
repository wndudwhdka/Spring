package com.kh.spring16;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring16.dto.PocketmonDto;

import lombok.extern.slf4j.Slf4j;

//목표 : mybatis를 이용한 pocketmon 조회
@Slf4j
@SpringBootTest
public class Test01 {
	
	//SqlSession은 myBatis에서 제공하는 데이터베이스 실행 도구
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		List<PocketmonDto> list = sqlSession.selectList("pocketmon.selectList");
		//log.debug("개수 = {}", list.size());
		for(PocketmonDto dto : list) {
			log.debug("dto = {}", dto);
		}
	}
	
}




