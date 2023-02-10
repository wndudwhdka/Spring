package com.kh.spring12.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.MemberStatDto;

@Repository
public class MemberStatDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<MemberStatDto> mapper = new RowMapper<MemberStatDto>(){

		@Override
		public MemberStatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberStatDto dto = new MemberStatDto();
			dto.setMember_level(rs.getString("Member_level"));
			dto.setCnt(rs.getInt("cnt"));
			dto.setPointSum(rs.getInt("pointsum"));
			dto.setPointAvg(rs.getInt("pointAvg"));
			dto.setPointMax(rs.getInt("pointMax"));
			dto.setPointMin(rs.getInt("pointmin"));
			return dto;
		}
		
	};
	
	public List<MemberStatDto> selectList(){
		String sql = "select * from member_stat";
		return jdbcTemplate.query(sql, mapper);
	}
	
	public List<MemberStatDto> selectList(String sort){
		String sql ="select * from member_stat order by "+ sort;
		return jdbcTemplate.query(sql,mapper);
	}
}
