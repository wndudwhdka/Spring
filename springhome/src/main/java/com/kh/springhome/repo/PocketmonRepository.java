package com.kh.springhome.repo;

import java.util.List;

import com.kh.springhome.dto.PocketmonDto;

//포켓몬스터 추상체
//- 외부에서 사용하는 대표 형태(간판, 목차)
public interface PocketmonRepository {
	void insert(PocketmonDto pocketmonDto);
	List<PocketmonDto> selectList();
	List<PocketmonDto> selectList(String column, String keyword);
	PocketmonDto selectOne(int no);
	boolean update(PocketmonDto pocketmonDto);
	boolean delete(int no);
}
