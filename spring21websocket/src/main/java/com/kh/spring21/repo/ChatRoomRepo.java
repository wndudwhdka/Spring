package com.kh.spring21.repo;

import java.util.List;

import com.kh.spring21.dto.ChatRoomDto;

public interface ChatRoomRepo {
	int sequence();
	void create(ChatRoomDto dto);
	ChatRoomDto find(String roomName);
	List<ChatRoomDto> list();
}