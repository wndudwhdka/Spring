package com.kh.spring12.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring12.dto.PocketmonWithImageDto;

@Repository
public class PocketmonWithImageDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<PocketmonWithImageDto> mapper = new RowMapper<PocketmonWithImageDto>() {
		@Override
		public PocketmonWithImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PocketmonWithImageDto.builder()
						.no(rs.getInt("no"))
						.name(rs.getString("name"))
						.type(rs.getString("type"))
						.attachmentNo(
								rs.getObject("attachment_no") == null ? 
													null : rs.getInt("attachment_no")
						)
					.build();
		}
	};
	
	public List<PocketmonWithImageDto> selectList() {
		String sql = "select * from pocketmon_with_image order by no asc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	public List<PocketmonWithImageDto> selectList(String column, String keyword) {
		String sql = "select * from pocketmon_with_image "
				+ "where instr(#1, ?) > 0 "
				+ "order by no asc";
		sql = sql.replace("#1", column);
		Object[] param = {keyword};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	public PocketmonWithImageDto selectOne(int no) {
		String sql = "select * from pocketmon_with_image where no=?";
		Object[] param = {no};
		List<PocketmonWithImageDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
}




