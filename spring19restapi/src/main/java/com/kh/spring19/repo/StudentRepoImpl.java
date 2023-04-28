package com.kh.spring19.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring19.dto.StudentDto;

@Repository
public class StudentRepoImpl implements StudentRepo {

	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public List<StudentDto> selectList() {
		return sqlSession.selectList("student.list");
	}

	@Override
	public StudentDto selectOne(int no) {
		return sqlSession.selectOne("student.find", no);
	}

	@Override
	public void insert(StudentDto studentDto) {
		sqlSession.insert("student.add", studentDto);
	}

	@Override
	public boolean update(StudentDto studentDto) {
		return sqlSession.update("student.edit", studentDto) > 0;
	}

	@Override
	public boolean delete(int no) {
		return sqlSession.delete("student.delete", no) > 0;
	}
	
}
