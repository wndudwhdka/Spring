package com.kh.spring19.repo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring19.dto.PocketmonDto;

@Repository
public class PocketmonRepoImpl implements PocketmonRepo{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(PocketmonDto dto) {
		System.out.println("dto = " + dto);
		sqlSession.insert("pocketmon.add", dto);
	}
	
	@Override
	public List<PocketmonDto> selectList() {
		return sqlSession.selectList("pocketmon.selectList");
	}
	
	@Override
	public PocketmonDto selectOne(int no) {
		return sqlSession.selectOne("pocketmon.selectOne", no);
	}
	
	@Override
	public boolean update(PocketmonDto dto) {
		return sqlSession.update("pocketmon.edit", dto) > 0;
	}
	
	@Override
	public boolean delete(int no) {
		return sqlSession.delete("pocketmon.remove", no) > 0;
	}

	@Override
	public List<PocketmonDto> selectListByName(String name) {
		return sqlSession.selectList("pocketmon.searchName", name);
	}
	
	@Override
	public List<PocketmonDto> selectListByPaging(int page) {
		int end = page * 10;
		int begin = end - 9;
		Map<String, Object> param = Map.of("begin", begin, "end", end);
		return sqlSession.selectList("pocketmon.infinite", param);
	}
	
}



