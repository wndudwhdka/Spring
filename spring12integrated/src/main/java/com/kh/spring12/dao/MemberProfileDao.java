package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.MemberProfileDto;

@Repository
public class MemberProfileDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	private RowMapper<MemberProfileDto> mapper = new RowMapper<MemberProfileDto>() {

		@Override
		public MemberProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return MemberProfileDto.builder()
					.memberId(rs.getString("member_id"))//
					.attachmentNo(rs.getInt("attachment_no"))
					.build();
		}
	};
	
	public void insert(MemberProfileDto memberProfileDto) {
		String sql = "insert into member_profile(member_id,attachment_no) values(?,?)";
		Object[] param = {memberProfileDto.getMemberId(), memberProfileDto.getAttachmentNo()};
		jdbcTemplate.update(sql,param);
	}
	

}
