package com.kh.spring20.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring20.dto.SecurityDto;

@Repository
public class SecurityRepoImpl implements SecurityRepo {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void insert(SecurityDto securityDto) {
		String encrypt = encoder.encode(securityDto.getMemberPw());
		securityDto.setMemberPw(encrypt);
		sqlSession.insert("security.join", securityDto);
	}
	
	@Override
	public boolean login(SecurityDto securityDto) {
		//securityDto는 사용자가 입력한 아이디/비밀번호가 있다
		//아이디를 이용하여 정보를 찾은 뒤 비교하도록 encoder에게 부탁
		//encode.matches(입력값, 암호화된값)
		SecurityDto findDto = sqlSession.selectOne("security.find", securityDto);
//		if(securityDto.getMemberPw().equals(findDto.getMemberPw()))
		if(encoder.matches(securityDto.getMemberPw(), findDto.getMemberPw())) {
			return true;
		}
		return false;
	}
	
}




