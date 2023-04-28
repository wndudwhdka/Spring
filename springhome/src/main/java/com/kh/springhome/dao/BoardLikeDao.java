package com.kh.springhome.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.springhome.dto.BoardLikeDto;

@Repository
public class BoardLikeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//등록
	public void insert(BoardLikeDto boardLikeDto) {
		String sql = "insert into board_like(member_id, board_no) values(?, ?)";
		Object[] param = {boardLikeDto.getMemberId(), boardLikeDto.getBoardNo()};
		jdbcTemplate.update(sql, param);
	}
	
	//삭제
	public void delete(BoardLikeDto boardLikeDto) {
		String sql = "delete board_like where member_id = ? and board_no = ?";
		Object[] param = {boardLikeDto.getMemberId(), boardLikeDto.getBoardNo()};
		jdbcTemplate.update(sql, param);
	}
	
	//확인 - count
	public boolean check(BoardLikeDto boardLikeDto) {
		String sql = "select count(*) from board_like where member_id = ? and board_no = ?";
		Object[] param = {boardLikeDto.getMemberId(), boardLikeDto.getBoardNo()};
		int count = jdbcTemplate.queryForObject(sql, int.class, param);
		return count == 1;
	}
	
	//글에 대한 좋아요 개수
	public int count(int boardNo) {
		String sql = "select count(*) from board_like where board_no = ?";
		Object[] param = {boardNo};
		return jdbcTemplate.queryForObject(sql, int.class, param);
	}
	
}








