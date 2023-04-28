package com.kh.springhome.repo;
//포켓몬스터 Spring JDBC 구현체

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.springhome.dto.PocketmonDto;

@Repository
public class PocketmonRepositorySpringJdbc implements PocketmonRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

	@Override
	public void insert(PocketmonDto pocketmonDto) {
		String sql = "insert into pocketmon(no,name,type) values(?,?,?)";
		Object[] param = {
				pocketmonDto.getNo(), 
				pocketmonDto.getName(), 
				pocketmonDto.getType()
		};
		jdbcTemplate.update(sql, param);
	}

	@Override
	public List<PocketmonDto> selectList() {
		String sql = "select * from pocketmon order by no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
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

	@Override
	public boolean update(PocketmonDto pocketmonDto) {
		String sql = "update pocketmon set name=?,type=? where no=?";
		Object[] param = {
				pocketmonDto.getName(), 
				pocketmonDto.getType(), 
				pocketmonDto.getNo()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}

	@Override
	public boolean delete(int no) {
		String sql = "delete pocketmon where no=?";
		Object[] param = {no};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	@Override
	public List<PocketmonDto> selectList(String column, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
