package com.kh.spring17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring17.dto.SubjectDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test06 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
//		목록
//		List<SubjectDto> list = sqlSession.selectList("subject.search");
//		List<SubjectDto> list = sqlSession.selectList("subject.search", null);
		
//		검색
		Map<String, Object> param = new HashMap<>();
		param.put("column", "name");
		param.put("keyword", "자바");
		List<SubjectDto> list = sqlSession.selectList("subject.search", param);
		
		for(SubjectDto dto : list) {
			log.debug("dto = {}", dto);
		}
	}
	
}
