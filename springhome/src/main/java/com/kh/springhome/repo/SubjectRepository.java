package com.kh.springhome.repo;

import java.util.List;

import com.kh.springhome.dto.SubjectDto;

public interface SubjectRepository {
	void insert(SubjectDto subjectDto);
	List<SubjectDto> selectList();
	List<SubjectDto> selectList(String column, String keyword);
	SubjectDto selectOne(int no);
	boolean update(SubjectDto subjectDto);
	boolean delete(int no);
}
