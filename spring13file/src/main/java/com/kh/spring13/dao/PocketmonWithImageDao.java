package com.kh.spring13.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.spring13.dto.PocketmonWithImageDto;

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
	
}




