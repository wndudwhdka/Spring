package com.kh.spring19.repo;

import java.util.List;

import com.kh.spring19.dto.SubjectDto;

public interface SubjectRepo {
	List<SubjectDto> selectList();
	SubjectDto selectOne(int no);
	void insert(SubjectDto subjectDto);
	boolean update(SubjectDto subjectDto);
	boolean delete(int no);
}
