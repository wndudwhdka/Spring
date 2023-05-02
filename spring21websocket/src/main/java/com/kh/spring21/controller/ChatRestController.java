package com.kh.spring21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring21.dto.ChatMessageDto;
import com.kh.spring21.repo.ChatMessageRepo;

@RestController("/rest")
public class ChatRestController {
	
	@Autowired
	private ChatMessageRepo chatMessageRepo;
	
	@GetMapping("/message/{roomName}")
	public List<ChatMessageDto> roomMessage(
			@PathVariable String roomName){
		return chatMessageRepo.roomMessageList(roomName);		
	}
	
}
