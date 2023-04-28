package com.kh.spring19.repo;

import java.util.List;

import com.kh.spring19.dto.PocketmonDto;

public interface PocketmonRepo {
	void insert(PocketmonDto dto);
	List<PocketmonDto> selectList();
	PocketmonDto selectOne(int no);
	boolean update(PocketmonDto dto);
	boolean delete(int no);
	
	List<PocketmonDto> selectListByName(String name);
	List<PocketmonDto> selectListByPaging(int page);
}







