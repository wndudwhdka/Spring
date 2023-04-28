package com.kh.spring16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring16.dto.PocketmonDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test06 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test( ) {
		//목표 : 키워드 검색(column, keyword)
		//(ex) column="name", keyword="피카" 라면
		//	-> select * from pocketmon where instr(name, '피카') > 0 order by name asc;
		Map<String, Object> param = new HashMap<>();
		param.put("column", "name");
		param.put("keyword", "피카");
		List<PocketmonDto> list = sqlSession.selectList("pocketmon.search", param);
		for(PocketmonDto dto : list) {
			log.debug("dto = {}", dto);
		}
	}
	
}





