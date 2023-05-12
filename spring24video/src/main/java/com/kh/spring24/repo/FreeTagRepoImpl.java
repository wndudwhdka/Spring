package com.kh.spring24.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring24.dto.FreeTagDto;

@Repository
public class FreeTagRepoImpl implements FreeTagRepo{

	@Autowired
	private SqlSession sqlSession; 
	
	@Override
	public Long sequence() {
		return sqlSession.selectOne("freeTag.sequence");
	}

	@Override
	public void insert(FreeTagDto freeTagDto) {
		sqlSession.insert("freeTag.insert",freeTagDto); 
	}

	@Override
	public boolean delete(Long freeTagNo) {
		return sqlSession.delete("freeTag.delete",freeTagNo) > 0 ; 
	}

	@Override
	public Long selectOne(String freeTagName) {
		return sqlSession.selectOne("freeTag.selectOne",freeTagName); 
	}

}
