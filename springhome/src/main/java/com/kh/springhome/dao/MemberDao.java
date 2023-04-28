package com.kh.springhome.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.springhome.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(MemberDto memberDto) {
		String sql = "insert into member("
					+ "member_id, member_pw, member_nick,"
					+ "member_tel, member_email, member_birth,"
					+ "member_post, member_basic_addr, member_detail_addr,"
					+ "member_level, member_point, member_join, member_login"
				+ ") values("
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, '준회원', 0, sysdate, null"
				+ ")";
		Object[] param = {
			memberDto.getMemberId(), memberDto.getMemberPw(),
			memberDto.getMemberNick(), memberDto.getMemberTel(),
			memberDto.getMemberEmail(), memberDto.getMemberBirth(),
			memberDto.getMemberPost(), memberDto.getMemberBasicAddr(),
			memberDto.getMemberDetailAddr()
		};
		jdbcTemplate.update(sql, param);
	}
	
	//조회를 위한 RowMapper 구현
	private RowMapper<MemberDto> mapper = new RowMapper<MemberDto>() {
		@Override
		public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId(rs.getString("member_id"));
			memberDto.setMemberPw(rs.getString("member_pw"));
			memberDto.setMemberNick(rs.getString("member_nick"));
			memberDto.setMemberTel(rs.getString("member_tel"));
			memberDto.setMemberEmail(rs.getString("member_email"));
			memberDto.setMemberBirth(rs.getString("member_birth"));
			memberDto.setMemberPost(rs.getString("member_post"));
			memberDto.setMemberBasicAddr(rs.getString("member_basic_addr"));
			memberDto.setMemberDetailAddr(rs.getString("member_detail_addr"));
			memberDto.setMemberLevel(rs.getString("member_level"));
			memberDto.setMemberPoint(rs.getInt("member_point"));
			memberDto.setMemberJoin(rs.getDate("member_join"));
			memberDto.setMemberLogin(rs.getDate("member_login"));
			return memberDto;
		}
	};
	
	public MemberDto selectOne(String memberId) {
		String sql = "select * from member where member_id = ?";
		Object[] param = {memberId};
		List<MemberDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
//	최종 로그인 시각만 갱신하는 기능(로그인 성공 시 호출)
	public boolean updateMemberLogin(String memberId) {
		String sql = "update member "
						+ "set member_login = sysdate "
						+ "where member_id = ?";
		Object[] param = {memberId};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
//	비밀번호 변경 기능
	public boolean changePassword(String memberId, String memberPw) {
		String sql = "update member set member_pw = ? where member_id = ?";
		Object[] param = {memberPw, memberId};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
//	비밀번호를 제외한 나머지 정보 변경 기능
	public boolean changeInformation(MemberDto memberDto) {
		String sql = "update member set "
						+ "member_nick=?, member_tel=?, "
						+ "member_email=?, member_birth=?, "
						+ "member_post=?, member_basic_addr=?, "
						+ "member_detail_addr=? "
						+ "where member_id = ?";
		Object[] param = {
			memberDto.getMemberNick(), memberDto.getMemberTel(),
			memberDto.getMemberEmail(), memberDto.getMemberBirth(),
			memberDto.getMemberPost(), memberDto.getMemberBasicAddr(),
			memberDto.getMemberDetailAddr(), memberDto.getMemberId()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}

//	회원 삭제(탈퇴)
	public boolean delete(String memberId) {
		String sql = "delete member where member_id = ?";
		Object[] param = {memberId};
		return jdbcTemplate.update(sql, param) > 0;
	}
	
//	닉네임, 전화번호, 생년월일로 아이디 찾기
	public String findId(MemberDto memberDto) {
		String sql = "select member_id from member "
				+ "where member_nick=? and member_tel=? and member_birth=?";
		Object[] param = {
			memberDto.getMemberNick(), memberDto.getMemberTel(),
			memberDto.getMemberBirth()
		};
		//String.class는 한글로 "String 자료형"이라는 뜻이다
		return jdbcTemplate.queryForObject(sql, String.class, param);
	}
	
//	목록 및 검색 
//	- 페이지 번호(page)와 페이지 크기(size)를 이용하여 계산
//	- Top N Query 사용
	public List<MemberDto> selectListPaging(int page, int size) {
		int end = page * size;
		int begin = end - (size-1);
		String sql = "select * from ("
							+ "select TMP.*, rownum RN from ("
								+ "select * from member order by member_id asc"
							+ ")TMP"
						+ ") where rn between ? and ?";
		Object[] param = {begin, end};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
//	카운트 구하는 기능
	public int selectCount() {
		String sql = "select count(*) from member";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	public void insertWaiting(MemberDto memberDto) {
		String sql = "insert into waiting("
				+ "member_id, member_pw, member_nick,"
				+ "member_tel, member_email, member_birth,"
				+ "member_post, member_basic_addr, member_detail_addr,"
				+ "member_level, member_point, member_join, member_login"
			+ ") values("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
			+ ")";
		Object[] param = {
			memberDto.getMemberId(), memberDto.getMemberPw(),
			memberDto.getMemberNick(), memberDto.getMemberTel(),
			memberDto.getMemberEmail(), memberDto.getMemberBirth(),
			memberDto.getMemberPost(), memberDto.getMemberBasicAddr(),
			memberDto.getMemberDetailAddr(), memberDto.getMemberLevel(),
			memberDto.getMemberPoint(), memberDto.getMemberJoin(),
			memberDto.getMemberLogin()
		};
		jdbcTemplate.update(sql, param);
	}
	
//	관리자용 회원 정보 변경
	public boolean changeInformationByAdmin(MemberDto memberDto) {
		String sql = "update member set "
						+ "member_nick=?, member_tel=?, member_birth=?,"
						+ "member_email=?, member_post=?, member_basic_addr=?,"
						+ "member_detail_addr=?, member_level=?, member_point=? "
						+ "where member_id = ?";
		Object[] param = {
			memberDto.getMemberNick(), memberDto.getMemberTel(),
			memberDto.getMemberBirth(), memberDto.getMemberEmail(),
			memberDto.getMemberPost(), memberDto.getMemberBasicAddr(),
			memberDto.getMemberDetailAddr(), memberDto.getMemberLevel(),
			memberDto.getMemberPoint(), memberDto.getMemberId()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}

	public MemberDto selectByNickname(String memberNick) {
		String sql = "select * from member where member_nick = ?";
		Object[] param = {memberNick};
		List<MemberDto> list = jdbcTemplate.query(sql, mapper, param);
		return list.isEmpty() ? null : list.get(0);
	}
	
}










