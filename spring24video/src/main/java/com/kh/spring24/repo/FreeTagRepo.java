package com.kh.spring24.repo;

import com.kh.spring24.dto.FreeTagDto;

//import com.kh.spring24.dto.freeTagDto;

public interface FreeTagRepo {
	// 자유태그 시퀀스 발행
	Long sequence();
	// 자유태그 등록
	void insert(FreeTagDto freeTagDto);
	// 업데이트는 필요 x 
	// 하나의 태그명 조회( 번호가 있는 지를 알려줌)
	Long selectOne(String freeTagName); 
	// 자유태그 삭제
	boolean delete(Long freeTagNo); 
}
