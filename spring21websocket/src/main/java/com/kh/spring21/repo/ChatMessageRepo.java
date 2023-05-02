package com.kh.spring21.repo;

import java.util.List;

import com.kh.spring21.dto.ChatMessageDto;

public interface ChatMessageRepo {
	void add(ChatMessageDto dto);
	List<ChatMessageDto> roomMessage() ;
}
