package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public void join(MemberDto memberDto) {
		 String sql = "insert into member("
		 		+ "member_id, member_pw, member_nick,"
		 		+ "member_tel,member_email,member_birth,"
		 		+ "member_post,member_basic_addr,member_detail_addr,"
		 		+ "member_level,member_point,member_join,member_login"
		 		+ ") values("
		 		+ "?,?,?,?,?,?,?,?,?,'준회원',0,sysdate,null)"; 
		 Object[] param = {
				 memberDto.getMemberId(),memberDto.getMemberPw(),
				 memberDto.getMemberNick(),memberDto.getMemberTel(),
				 memberDto.getMemberEmail(),memberDto.getMemberBirth(),
				 memberDto.getMemberPost(),memberDto.getMemberBasicAddr(),
				 memberDto.getMemberDetailAddr()};
		 jdbcTemplate.update(sql,param); 
	}
	
	private RowMapper<MemberDto> mapper = new RowMapper<MemberDto>() {

		@Override
		public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDto memberDto = new MemberDto(); 
			memberDto.setMemberId(rs.getString("member_id"));
			memberDto.setMemberPw(rs.getString("member_pw"));
			memberDto.setMemberNick(rs.getString("member_nick"));
			memberDto.setMemberTel(rs.getString("member_tel"));
			memberDto.setMemberEmail(rs.getString("member_email"));
			memberDto.setMemberBirth(rs.getString("member_birth"));
			memberDto.setMemberPost(rs.getString("member_post"));
			memberDto.setMemberBasicAddr(rs.getString("member_basic_addr"));
			memberDto.setMemberDetailAddr(rs.getString("member_detail_addr"));
			memberDto.setMemberLevel(rs.getString("member_level"));
			memberDto.setMemberPoint(rs.getInt("member_point"));
			memberDto.setMemberJoin(rs.getDate("member_join"));
			memberDto.setMemberLogin(rs.getDate("member_login"));
			return memberDto;
		}
		
	};
	
	public MemberDto selectOne(String memberId) {
		String sql =  "select * from member where member_id =?"; 
		Object[] param = {memberId};
		List<MemberDto> list = jdbcTemplate.query(sql,mapper,param); 
		return list.isEmpty() ? null:list.get(0);
	}

	// 로그인 성공 시 호출하는 dao 
	public boolean updateMemberLogin(String memberId) {
		String sql = "update member "
				+ "set member_login = sysdate "
				+ "where member_id = ?";
		Object[] param = { memberId};
		return jdbcTemplate.update(sql,param) > 0; 
		
	}
	
}
