package com.kh.spring19.repo;

import java.util.List;

import com.kh.spring19.dto.StudentDto;

public interface StudentRepo {
	List<StudentDto> selectList();
	StudentDto selectOne(int no);
	void insert(StudentDto studentDto);
	boolean update(StudentDto studentDto);
	boolean delete(int no);
}
