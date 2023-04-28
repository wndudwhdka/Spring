package com.kh.spring18;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring18.dto.BoardDto;
import com.kh.spring18.vo.MemberWithBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		List<MemberWithBoardVO> list = 
				sqlSession.selectList("member.memberWithBoard");
		for(MemberWithBoardVO vo : list) {
			log.debug("회원정보 = {}", vo.getMemberDto());
			for(BoardDto boardDto : vo.getBoardList()) {
				log.debug("게시글정보 = {}", boardDto);
			}
		}
	}
	
}

