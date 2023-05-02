package com.kh.spring21.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ChatRoomDto {
	private String roomName;
	private Date roomCreated;
}