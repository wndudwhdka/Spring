package com.kh.spring16;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring16.dto.PocketmonDto;
import com.kh.spring16.vo.PocketmonCountVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test09 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		PocketmonDto dto = new PocketmonDto();
		dto.setNo(1);
		dto.setName("라이츄");
		dto.setType("전기");
		int result = sqlSession.update("pocketmon.editAllInOne", dto);
		log.debug("result = {}", result);
	}
	
}
