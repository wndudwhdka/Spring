package com.kh.spring19.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring19.dto.SubjectDto;

@Repository
public class SubjectRepoImpl implements SubjectRepo {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SubjectDto> selectList() {
		return sqlSession.selectList("subject.list");
	}

	@Override
	public SubjectDto selectOne(int no) {
		return sqlSession.selectOne("subject.find", no);
	}

	@Override
	public void insert(SubjectDto subjectDto) {
		sqlSession.insert("subject.add", subjectDto);
	}

	@Override
	public boolean update(SubjectDto subjectDto) {
		return sqlSession.update("subject.edit", subjectDto) > 0;
	}

	@Override
	public boolean delete(int no) {
		return sqlSession.delete("subject.delete", no) > 0;
	}
	
}
