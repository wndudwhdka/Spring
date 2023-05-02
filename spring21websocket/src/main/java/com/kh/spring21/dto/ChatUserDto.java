package com.kh.spring21.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ChatUserDto {
	private String roomName;
	private String memberId;
	private Date joinTime;
}