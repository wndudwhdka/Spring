package com.kh.spring17;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring17.dto.SubjectDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01 {
	
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		List<SubjectDto> list = sqlSession.selectList("subject.list");
//		List<SubjectDto> list = sqlSession.selectList("subject.listOrFind");
		for(SubjectDto dto : list) {
			log.debug("dto = {}", dto);
		}
	}
	
}




