package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.BoardDto;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<BoardDto> mapper = new RowMapper<BoardDto>() {

		@Override
		public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardDto dto = new BoardDto(); 
			dto.setBoardNo(rs.getInt("board_no"));
			dto.setBoardWriter(rs.getString("board_writer"));
			dto.setBoardTitle(rs.getString("board_title"));
			dto.setBoardContent(rs.getString("board_content"));
			dto.setBoardTime(rs.getDate("board_time"));
			dto.setBoardHead(rs.getString("board_head"));
			dto.setBoardRead(rs.getInt("board_read"));
			dto.setBoardLike(rs.getInt("board_like"));
			dto.setBoardReply(rs.getInt("board_reply"));
			return dto;
		}
	}; 
	
 
//	public List<BoardDto> selectHeadList(){
//		String sql = "select board_no,board_head,board_read,board_like from board"; 
//		return jdbcTemplate.query(sql, mapper);
//	}
	
	// 총 게시물 수를 확인하기 위한 코드
	public int selectCount() {
		String sql = "select count(*) from board";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	// 게시물 게시 페이지 
	public List<BoardDto> selectListPaging(int page,int size)
	{
		// 페이지 끝 계산
		int end = page * size;
		// 시작 페이지 계산
		int begin = end - (size-1);
		String sql = "select * from ("
				+ "select TMP.*, rownum RN from("
				+ "select * from board order by board_no asc"
				+ ")TMP "
				+ ") where RN between ? and ?";	
		Object[] param = {begin, end};
		
		return jdbcTemplate.query(sql,mapper,param); 
		
	}
	
	public BoardDto selectOne(int no) {
		String sql = "select * from board where board_no=?"; 
		Object[] param = {no};
		List<BoardDto> list = jdbcTemplate.query(sql, mapper,param); 
		if(list.isEmpty())
		{
			return null; 
		}
		else
		{
			return list.get(0); 
		}
	}
	
	public boolean delete(int no) {
		String sql = "delete board where board_no=?";
		Object[] param = {no};
		return jdbcTemplate.update(sql,param) > 0; 
	}
	
	public boolean write(BoardDto boardDto) {
		String sql = "update board set board_content=? "
				+ "where board_no=?";
		Object[] param = { boardDto.getb
	}
}
