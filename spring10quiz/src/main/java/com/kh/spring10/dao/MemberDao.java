package com.kh.spring10.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring10.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	private RowMapper<MemberDto> mapper = new RowMapper<MemberDto>() {
		@Override
		public MemberDto mapRow(ResultSet rs, int idx) throws SQLException {
			MemberDto dto = new MemberDto();
			dto.setMemberId(rs.getString("member_id"));
			dto.setMemberPw(rs.getString("member_pw"));
			dto.setMemberNick(rs.getString("member_nick"));
			dto.setMemberTel(rs.getString("member_tel"));
			dto.setMemberEmail(rs.getString("member_email"));
			dto.setMemberBirth(rs.getString("member_birth"));
			dto.setMemberPost(rs.getString("member_post"));
			dto.setMemberBasicAddr(rs.getString("member_basic_addr"));
			dto.setMemberDetailAddr(rs.getString("member_detail_addr"));
			dto.setMemberPoint(rs.getInt("member_point"));
			dto.setMemberLevel(rs.getString("member_level"));
			dto.setMemberJoin(rs.getDate("member_join"));
			dto.setMemberLogin(rs.getDate("member_login"));
			return dto;
		}
	};
	
	public void insert(MemberDto dto) {	
		String sql = "insert into member("
						+ "member_id, member_pw, member_nick,"
						+ "member_tel, member_email, member_birth,"
						+ "member_post, member_basic_addr, member_detail_addr,"
						+ "member_level, member_point, member_join, member_login"
						+ ") "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, '준회원', 0, sysdate, null)";
		Object[] param = {
			dto.getMemberId(), dto.getMemberPw(), dto.getMemberNick(),
			dto.getMemberTel(), dto.getMemberEmail(), dto.getMemberBirth(),
			dto.getMemberPost(), dto.getMemberBasicAddr(), dto.getMemberDetailAddr()
		};
		
		jdbcTemplate.update(sql, param);
			
	}
}
