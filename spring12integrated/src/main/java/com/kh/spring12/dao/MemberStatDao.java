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
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MemberStatDto> mapper = new RowMapper<MemberStatDto>() {
		@Override
		public MemberStatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberStatDto dto = new MemberStatDto();
			dto.setMemberLevel(rs.getString("member_level"));
			dto.setTotal(rs.getInt("total"));
			dto.setCnt(rs.getInt("cnt"));
			dto.setAverage(rs.getFloat("average"));
			dto.setMax(rs.getInt("max"));
			dto.setMin(rs.getInt("min"));
			return dto;
		}
	};
	
	public List<MemberStatDto> selectList(){
		String sql = "select * from member_stat order by member_level asc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	//(+추가) 정렬을 자유자재로 할 수 있는 리스트 구문
	public List<MemberStatDto> selectList(String sort) {
		String sql = "select * from member_stat order by " + sort;
		return jdbcTemplate.query(sql, mapper);
	}
	
}







