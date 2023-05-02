package com.kh.spring21.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ChatMessageDto {
	private int messageNo;
	private String memberId;
	private String roomName;
	private String messageBody;
	private Date messageTime;
}
