package com.kh.spring16;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring16.vo.PocketmonCountVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test08 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//pocketmon-mapper에 생성한 countByType 구문을 호출
		List<PocketmonCountVO> list = 
						sqlSession.selectList("pocketmon.countByType");
		for(PocketmonCountVO vo : list) {
			log.debug("vo = {}", vo);
		}
	}
	
}
