package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.StudentDto;

@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<StudentDto> mapper = new RowMapper<StudentDto>() {
		
		@Override
		public StudentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentDto studentDto = new StudentDto();
			studentDto.setNo(rs.getInt("no"));
			studentDto.setName(rs.getString("name"));
			studentDto.setKorean(rs.getInt("korean"));
			studentDto.setEnglish(rs.getInt("english"));
			studentDto.setMath(rs.getInt("math"));
			return studentDto;
		}
	};
	
	public void insert(StudentDto studentDto) {
		String sql = "insert into student(no,name,korean,english,math) values(student_seq.nextval, ?, ?, ?, ?)";
		Object[] param = {
			studentDto.getName(), studentDto.getKorean(),
			studentDto.getEnglish(), studentDto.getMath()
		};
		jdbcTemplate.update(sql, param);
	}
	
	public List<StudentDto> selectList(){
		String sql = "select * from student order by no asc";
		return jdbcTemplate.query(sql, mapper);
	}
	public List<StudentDto> selectList(String column, String keyword) {
		String sql = "select * from student where instr(#1, ?) > 0 order by #1 asc";
		sql = sql.replace("#1", column);
		Object[] param = {keyword};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	public StudentDto selectOne(int no) {
		String sql = "select * from student where no = ?";
		Object[] param = {no};
		List<StudentDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public boolean update(StudentDto studentDto) {
		String sql = "update student set name=?,korean=?,english=?,math=? where no=?";
		Object[] param = {
			studentDto.getName(), studentDto.getKorean(),
			studentDto.getEnglish(), studentDto.getMath(),
			studentDto.getNo()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	public boolean delete(int no) {
		String sql = "delete student where no = ?";
		Object[] param = {no};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
}
