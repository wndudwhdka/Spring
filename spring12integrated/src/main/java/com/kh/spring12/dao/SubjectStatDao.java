package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.SubjectStatDto;

@Repository
public class SubjectStatDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<SubjectStatDto> mapper = new RowMapper<SubjectStatDto>() {

		@Override
		public SubjectStatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectStatDto dto = new SubjectStatDto();
			dto.setType(rs.getString("type"));
			dto.setCnt(rs.getInt("cnt"));
			dto.setAver(rs.getDouble("aver"));
			return dto;
		}
	};
	
	
	public List<SubjectStatDto> selectList(){
		String sql = "select * from subject_stat";
		return jdbcTemplate.query(sql, mapper);
	}
	
}
