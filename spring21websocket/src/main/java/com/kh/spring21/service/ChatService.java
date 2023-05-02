package com.kh.spring21.service;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

//WebSocket에서 발생하는 작업과 DB 처리를 담당하는 서비스
public interface ChatService {
	void connectHandler(WebSocketSession session);
	void disconnectHandler(WebSocketSession session);
	void receiveHandler(WebSocketSession session, TextMessage message) throws IOException;
}