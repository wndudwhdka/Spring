package com.kh.spring12.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.ReplyDto;

/**
 *	댓글 기능 
 *	- 전체목록과 상세보기가 없다
 *	- 등록 기능
 *	- 게시글 별 댓글 목록
 *	- 수정 기능
 *	- 삭제 기능
 */

@Repository
public class ReplyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<ReplyDto> mapper = (rs, index)->{
		return ReplyDto.builder()
					.replyNo(rs.getInt("reply_no"))
					.replyWriter(rs.getString("reply_writer"))
					.replyOrigin(rs.getInt("reply_origin"))
					.replyContent(rs.getString("reply_content"))
					.replyTime(rs.getDate("reply_time"))
				.build();
	};
	
	public void insert(ReplyDto replyDto) {
		String sql = "insert into reply("
							+ "reply_no, reply_writer, reply_origin, "
							+ "reply_content, reply_time"
						+ ") "
						+ "values(reply_seq.nextval, ?, ?, ?, sysdate)";
		Object[] param = {
				replyDto.getReplyWriter(),
				replyDto.getReplyOrigin(),
				replyDto.getReplyContent()
		};
		jdbcTemplate.update(sql, param);
	}
	
	public List<ReplyDto> selectList(int replyOrigin) {
		String sql = "select * from reply "
						+ "where reply_origin = ? "
						+ "order by reply_no asc";
		Object[] param = {replyOrigin};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	public void update(ReplyDto replyDto) {
		String sql = "update reply set reply_content = ? where reply_no = ?";
		Object[] param = {
				replyDto.getReplyContent(), replyDto.getReplyNo()
		};
		jdbcTemplate.update(sql, param);
	}
	
	public void delete(int replyNo) {
		String sql = "delete reply where reply_no = ?";
		Object[] param = {replyNo};
		jdbcTemplate.update(sql, param);
	}

	public ReplyDto selectOne(int replyNo) {
		String sql = "select * from reply where reply_no = ?";
		Object[] param = {replyNo};
		List<ReplyDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
}










