package com.kh.springhome.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springhome.dao.BoardDao;
import com.kh.springhome.dao.ReplyDao;
import com.kh.springhome.dto.ReplyDto;

@RestController
@RequestMapping("/rest/reply")
public class ReplyRestController {
	
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private BoardDao boardDao;

	@GetMapping("/{replyOrigin}")
	public List<ReplyDto> list(@PathVariable int replyOrigin) {
		return replyDao.selectList(replyOrigin);
	}
	
	@PostMapping("/")
	public void write(HttpSession session, 
				@ModelAttribute ReplyDto replyDto) {
		//작성자 설정
		String memberId = (String)session.getAttribute("memberId");
		replyDto.setReplyWriter(memberId);
		
		//등록
		replyDao.insert(replyDto);
		
		boardDao.updateReplycount(replyDto.getReplyOrigin());
	}
	
	@DeleteMapping("/{replyNo}")
	public void delete(@PathVariable int replyNo) {
		ReplyDto replyDto = replyDao.selectOne(replyNo);
		//삭제를 조회보다 나중에 해야 정보가 나옴
		replyDao.delete(replyNo);
		//댓글 삭제 후 개수 갱신
		boardDao.updateReplycount(replyDto.getReplyOrigin());
	}
	
//	@PutMapping("/")//전체수정
	@PatchMapping("/")//일부수정
	public void edit(@ModelAttribute ReplyDto replyDto) {
		replyDao.update(replyDto);
	}
	
}











