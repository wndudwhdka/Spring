package com.kh.springhome.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springhome.dao.BoardDao;
import com.kh.springhome.dao.BoardLikeDao;
import com.kh.springhome.dto.BoardLikeDto;
import com.kh.springhome.vo.BoardLikeVO;

@RestController
@RequestMapping("/rest/board")
public class BoardRestController {
	
	@Autowired
	private BoardLikeDao boardLikeDao;
	
	@Autowired
	private BoardDao boardDao;

	//조회는 GET으로 하는게 맞긴한데...
	//데이터가 많으면 주소 설정이 어렵다
	//----> POST로 처리하면 깔끔
	//@GetMapping("/like/{boardNo}")
	@PostMapping("/like")
	public BoardLikeVO like(HttpSession session, 
			@ModelAttribute BoardLikeDto boardLikeDto) {
		String memberId = (String)session.getAttribute("memberId");
		boardLikeDto.setMemberId(memberId);
		
		boolean current = boardLikeDao.check(boardLikeDto);
		if(current) {
			boardLikeDao.delete(boardLikeDto);
		}
		else {
			boardLikeDao.insert(boardLikeDto);
		}
		
		//좋아요 개수
		int count = boardLikeDao.count(boardLikeDto.getBoardNo());
		
		//게시글의 좋아요 개수를 업데이트
		boardDao.updateLikecount(boardLikeDto.getBoardNo(), count);
		
		return BoardLikeVO.builder()
					.result(!current)
					.count(count)
				.build();
		//return Map.of("result", !current, "count", count);
	}
	
	@PostMapping("/check")
	public boolean check(HttpSession session,
			@ModelAttribute BoardLikeDto boardLikeDto) {
		String memberId = (String)session.getAttribute("memberId");
		boardLikeDto.setMemberId(memberId);
		
		return boardLikeDao.check(boardLikeDto);
	}
	
}







