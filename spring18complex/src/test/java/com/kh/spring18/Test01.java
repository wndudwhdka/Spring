package com.kh.spring18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring18.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01 {
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Test
	public void test() {
		List<MemberDto> list = sqlSession.selectList("member.complexSearch");
		for(MemberDto memberDto : list) {
			log.debug("dto = {}", memberDto);
		}
	}
	
	@Test
	public void test2() {
		Map<String, Object> param = new HashMap<>();
//		param.put("memberId", "user");
//		param.put("memberNick", "유저");
//		param.put("memberLevelList", List.of("일반회원", "우수회원", "관리자"));
//		param.put("memberEmail", "kh.com");
//		param.put("memberBirth", "1999");
//		param.put("memberTel", "01024680011");
//		param.put("memberAddress", "서울시");
//		param.put("minPoint", 100);
//		param.put("maxPoint", 10000);
//		param.put("beginJoindate", "2023-02-01");
//		param.put("endJoindate", "2023-02-28");
//		param.put("searchLoginDays", 30);
		param.put("orderList", List.of(
//					"member_join desc",
//					"member_point desc"
				));
		List<MemberDto> list = sqlSession.selectList("member.complexSearch", param);
		log.debug("개수 = {}", list.size());
//		for(MemberDto memberDto : list) {
//			log.debug("dto = {}", memberDto);
//		}
	}
	
}
