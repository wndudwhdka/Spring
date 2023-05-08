package com.kh.spring22.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring22.dto.ItemDto;

@Repository
public class ItemRepoImpl implements ItemRepo{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ItemDto> list() {
		return sqlSession.selectList("item.list");
	}
	
	@Override
	public ItemDto find(int itemNo) {
		return sqlSession.selectOne("item.find", itemNo);
	}
	
}