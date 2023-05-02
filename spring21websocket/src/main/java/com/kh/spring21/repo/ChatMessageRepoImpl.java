package com.kh.spring21.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.spring21.dto.ChatMessageDto;

public class ChatMessageRepoImpl implements ChatMessageRepo{

	@Autowired
	private SqlSession sqlSession; 
	
	@Override
	public void add(ChatMessageDto dto) {
		sqlSession.insert("chatMessage.add",dto); 		
	}

	
}
