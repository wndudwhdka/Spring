package com.kh.springhome.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.springhome.dto.PocketmonImageDto;

@Repository
public class PocketmonImageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<PocketmonImageDto> mapper = new RowMapper<PocketmonImageDto>() {
		@Override
		public PocketmonImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PocketmonImageDto.builder()
						.pocketmonNo(rs.getInt("pocketmon_no"))
						.attachmentNo(rs.getInt("attachment_no"))
					.build();
		}
	};
	

	public void insert(PocketmonImageDto imageDto) {
		String sql = "insert into pocketmon_image values(?, ?)";
		Object[] param = {
				imageDto.getPocketmonNo(), imageDto.getAttachmentNo()
		};
		jdbcTemplate.update(sql, param);
	}
	
	public List<PocketmonImageDto> selectList() {
		String sql = "select * from pocketmon_image";
		return jdbcTemplate.query(sql, mapper);
	}
	
}







