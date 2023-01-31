package com.kh.spring09.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.StudentDto;

@Repository
public class StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(); 
	
	private RowMapper<StudentDto> mapper = new RowMapper<StudentDto>() {

		@Override
		public StudentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentDto dto = new StudentDto(); 
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setKorean(rs.getInt("korean"));
			dto.setEnglish(rs.getInt("english"));
			dto.setMath(rs.getInt("math"));
			return dto;
		}
	};
	

	public void insert(StudentDto dto)
	{
		String sql = "insert into student (no,name,korean,english,math) values(?,?,?,?,?)";
		Object[] param = {
				dto.getNo(),dto.getName(),dto.getKorean(),
				dto.getEnglish(),dto.getMath() 
		}; 
		jdbcTemplate.update(sql,param); 
	}

	public List<StudentDto> selectList()
	{
		String sql = "select * from student order by no asc"; 
		return jdbcTemplate.query(sql,mapper); 
	}
	
	public List<StudentDto> selectList(String column, String keyword)
	{
		 String sql = "select * from student "
		 		+ "where instr(#1,?) > 0 "
		 		+ "order by #1 asc";
		 sql = sql.replace("#1", column);
		 Object[] param = {keyword};
		 return jdbcTemplate.query(sql, mapper, param); 		 
	}
	
	public StudentDto selectOne(int no) {
		String sql = "select * from student where no =? "; 
		Object[] param = {no};
		List<StudentDto> list = jdbcTemplate.query(sql, mapper,param);
		if(list.isEmpty())
		{
			return null;
		}
		else{
			return list.get(0); 
		}
		
	}
	
	public boolean update(StudentDto dto) {
		String sql = "update student set name=?,korean=?,english=?,math=? where no=?";
		Object[] param = {
				dto.getName(),dto.getKorean(),
				dto.getEnglish(),dto.getMath(),dto.getNo()
		};
		return jdbcTemplate.update(sql,param) > 0; 
		
	}
	
	public boolean delete(int no)
	{
		String sql = "delete student where no=?";
		Object[] param = {no}; 
		return jdbcTemplate.update(sql,param) > 0; 
	}
}
