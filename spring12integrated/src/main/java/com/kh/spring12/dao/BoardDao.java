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

	private RowMapper<BoardDto> mapper = new RowMapper<BoardDto>() {
		@Override
		public BoardDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardNo(rs.getInt("board_no"));
			boardDto.setBoardWriter(rs.getString("board_writer"));
			boardDto.setBoardTitle(rs.getString("board_title"));
			boardDto.setBoardContent(rs.getString("board_content"));
			boardDto.setBoardTime(rs.getDate("board_time"));
			boardDto.setBoardHead(rs.getString("board_head"));
			boardDto.setBoardRead(rs.getInt("board_read"));
			boardDto.setBoardLike(rs.getInt("board_like"));
			boardDto.setBoardReply(rs.getInt("board_reply"));
			boardDto.setBoardGroup(rs.getInt("board_group"));
			boardDto.setBoardParent(rs.getInt("board_parent"));
			boardDto.setBoardDepth(rs.getInt("board_depth"));
			return boardDto;
		}
	};
	
	//공지사항만 조회하는 기능
	public List<BoardDto> selecNoticeList(int begin, int end){
		String sql = "select * from ("
				+ 	"select rownum m, TMP.* from ("
				+ 	"select * from board where board_head='공지' "
				+ "order by board_no asc"
				+ ")TMP )"
				+ "where m between ? and ?";
		Object[] param = { begin, end} ;
		return jdbcTemplate.query(sql,  mapper,param); 
		  
		
	}
	
	public List<BoardDto> selectList() {
		//String sql = "select * from board order by board_no asc";
		String sql = "select * from board "
				+ 	"connect by prior board_no=board_parent "
				+ " start with board_parent is null "
				+ "order siblings by board_group desc, board_no asc"; 
		return jdbcTemplate.query(sql, mapper);
	}
	
	public List<BoardDto> selectList(String column, String keyword) {
		String sql = "select * from board "
						+ "where instr(#1, ?) > 0 "
						+ "order by board_no desc";
		sql = sql.replace("#1", column);
		Object[] param = {keyword};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	public BoardDto selectOne(int boardNo) {
		String sql = "select * from board where board_no = ?";
		Object[] param = {boardNo};
		List<BoardDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
//	번호를 생성하면서 등록하는 방법
//	1. 시퀀스 번호를 듀얼 테이블을 사용하여 조회
//	2. 생성된 번호까지 설정한 DTO를 등록
	public int sequence() {
		String sql = "select board_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(BoardDto boardDto) {
		String sql = "insert into board("
				+ "board_no, board_writer, board_title, board_content, "
				+ "board_head, board_time, board_read, board_like, board_reply) "
				+ "values(?, ?, ?, ?, ?, sysdate, 0, 0, 0)";
		Object[] param = {
			boardDto.getBoardNo(), boardDto.getBoardWriter(),
			boardDto.getBoardTitle(), boardDto.getBoardContent(),
			boardDto.getBoardHead()
		};
		jdbcTemplate.update(sql, param);
	}
	
	public boolean delete(int boardNo) {
		String sql = "delete board where board_no = ?";
		Object[] param = {boardNo};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	//게시글 수정
	public boolean update(BoardDto boardDto) {
		String sql = "update board "
						+ "set board_head=?, board_title=?, board_content=? "
						+ "where board_no = ?";
		Object[] param = {
			boardDto.getBoardHead(), boardDto.getBoardTitle(),
			boardDto.getBoardContent(), boardDto.getBoardNo()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	//조회수 증가
	public boolean updateReadcount(int boardNo) {
		String sql = "update board set board_read=board_read+1 "
							+ "where board_no = ?";
		Object[] param = {boardNo};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
}








