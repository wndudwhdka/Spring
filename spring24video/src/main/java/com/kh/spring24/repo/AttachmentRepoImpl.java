package com.kh.spring24.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring24.dto.AttachmentDto;

@Repository
public class AttachmentRepoImpl implements AttachmentRepo{
	
    @Autowired
    private SqlSession sqlSession;

    @Override
    public int sequence() {
        return sqlSession.selectOne("attachment.sequence");
    }

    @Override
    public void insert(AttachmentDto attachmentDto) {
        sqlSession.insert("attachment.insert", attachmentDto);
    }

    @Override
    public AttachmentDto selectOne(int attachmentNo) {
        return sqlSession.selectOne("attachment.selectOne", attachmentNo);
    }

	
}
