package com.kh.spring09.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.SubjectDto;

@Repository
public class SubjectDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SubjectDto> mapper = new RowMapper<SubjectDto>() {

		public SubjectDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectDto dto = new SubjectDto();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setPeriod(rs.getInt("period"));
			dto.setPrice(rs.getInt("price"));
			dto.setType(rs.getInt("type"));
			return dto;
		}

		
	}; 
	
	public void insert(SubjectDto dto)
	{
		String sql = "insert into subject (no,name,period,price,type) values(?,?,?,?,?)";
		Object[] param = {
				dto.getNo(), dto.getName(), dto.getPeriod(),
				dto.getPrice(),dto.getType()};
		jdbcTemplate.update(sql,param); 
	}
	
	public List<SubjectDto> selectList(){
		String sql = "select * from subject order by no asc";
		return jdbcTemplate.query(sql,mapper);
	}
	
	public List<SubjectDto> selectList(String column,String keyword){
		String sql = "select * from subject where instr(#1,?) >0 "
				+ "order by #1 asc"; 
		sql = sql.replace("#1",column);
		Object[] param = {keyword};
		return jdbcTemplate.query(sql,mapper,param);
	}
	
	public SubjectDto selectOne(int no) {
		String sql = "select * from subject where no=?"; 
		Object[] param = {no};
		List<SubjectDto> list = jdbcTemplate.query(sql, mapper,param);
		if(list.isEmpty())
		{
			return null;
		}
		else {
			return list.get(0); 
		}
	}
	
	public boolean update(SubjectDto dto) {
		String sql = "update subject set name=?, period=?, price=?, type=? "
				+ "where no=?";
		Object[] param = {
				dto.getName(),dto.getPeriod(),dto.getPrice(),
				dto.getType(),dto.getNo()
		};
		return jdbcTemplate.update(sql,param)>0;
	}
	
	public boolean delete(int no)
	{
		String sql = "delete subject where no=?";
		Object[] param = {no}; 
		return jdbcTemplate.update(sql,param)> 0;
	}
	
}
