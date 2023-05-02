package com.kh.spring21.vo;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.Data;
import lombok.EqualsAndHashCode;

//한 명의 웹소켓 사용자 정보
@Data
//판정기준을 추가(WebSocketSession이 같으면 같은 사용자로 본다)
@EqualsAndHashCode(of = {"session"})
public class UserVO {
	private WebSocketSession session;
	private String memberId, memberLevel;
	
	public UserVO(WebSocketSession session) {
		this.session = session;
		Map<String,Object> attr = session.getAttributes();
		this.memberId = (String)attr.get("memberId");
		this.memberLevel = (String)attr.get("memberLevel"); 
	}
	
	public boolean isMember() {
		return this.memberId != null && this.memberLevel != null;
	}
	public void send(TextMessage jsonMessage) throws IOException {
		session.sendMessage(jsonMessage);		
	}
	
	
}