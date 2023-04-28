package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.PocketmonStatDto;

@Repository
public class PocketmonStatDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<PocketmonStatDto> mapper = new RowMapper<PocketmonStatDto>() {
		@Override
		public PocketmonStatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			PocketmonStatDto dto = new PocketmonStatDto();
			dto.setType(rs.getString("type"));
			dto.setCnt(rs.getInt("cnt"));
			return dto;
		}
	};
	
	public List<PocketmonStatDto> selectList(){
		String sql = "select * from pocketmon_stat";
		return jdbcTemplate.query(sql, mapper);
	}
	
}



