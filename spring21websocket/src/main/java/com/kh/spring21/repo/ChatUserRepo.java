package com.kh.spring21.repo;

import java.util.List;

import com.kh.spring21.dto.ChatUserDto;

public interface ChatUserRepo {
	void add(ChatUserDto dto);
	List<ChatUserDto> find(String memberId);
	boolean check(ChatUserDto userDto);
}