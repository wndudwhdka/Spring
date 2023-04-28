package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.PocketmonDto;

//DAO는 DB작업 전담 처리 도구이다
//- 일을 하려면 JdbcTemplate이 필요하다
//- JdbcTemplate은 Spring에 생성하여 등록된 상태
//- 이걸 가져다가 쓰려면 나도 등록이 되어 있어야 한다
//- @Repository로 등록하여 DB 또는 파일 작업을 하는 도구임을 알린다
@Repository
public class PocketmonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(PocketmonDto dto) {
		String sql = "insert into pocketmon(no,name,type) values(?,?,?)";
		Object[] param = {dto.getNo(), dto.getName(), dto.getType()};
		jdbcTemplate.update(sql, param);
	}
	
	private RowMapper<PocketmonDto> mapper = new RowMapper<PocketmonDto>() {
		@Override
		public PocketmonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			PocketmonDto dto = new PocketmonDto();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setType(rs.getString("type"));
			return dto;
		}
	};
	
	public List<PocketmonDto> selectList(){
		String sql = "select * from pocketmon order by no asc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	public List<PocketmonDto> selectList(String column, String keyword) {
		String sql = "select * from pocketmon "
						+ "where instr(#1,?) > 0 "
						+ "order by #1 asc";
		sql = sql.replace("#1", column);
		Object[] param = {keyword};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	public PocketmonDto selectOne(int no) {
		String sql = "select * from pocketmon where no = ?";
		Object[] param = {no};
		List<PocketmonDto> list = jdbcTemplate.query(sql, mapper, param);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean update(PocketmonDto dto) {
		String sql = "update pocketmon set name=?,type=? where no=?";
		Object[] param = {dto.getName(), dto.getType(), dto.getNo()};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	public boolean delete(int no) {
		String sql = "delete pocketmon where no=?";
		Object[] param = {no};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
}









