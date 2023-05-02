package com.kh.spring21.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

// 채널을 가진 웹소켓 서버에서 사용하는 메세지 수신 양식
// type=1 일 경우 채팅 메세지로 간주하며 content 항목이 필요
// type=2 일 경우 입장 메세지로 간주하며 room 항목이 필요
@Data 
public class ChannelReceiveVO {
	private int type;
	@JsonIgnore // 이 필드는 없을 수 있다(jackson)
	private String content;
	@JsonIgnore // 이 필드는 없을 수 있다(jackson)
	private String room; 
}
