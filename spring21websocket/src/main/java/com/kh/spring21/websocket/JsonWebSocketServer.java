package com.kh.spring21.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring21.vo.JsonMessageVO;

import lombok.extern.slf4j.Slf4j;

//JSON을 주고받는 웹소켓 서버
@Slf4j
@Service
public class JsonWebSocketServer extends TextWebSocketHandler {
	
	private Set<WebSocketSession> users = new CopyOnWriteArraySet<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, 
											TextMessage message) throws Exception {
		//message는 JSON 문자열 형태
		//-> message를 해석해서(payload) 원하는 정보를 추가한 뒤 전송
		//-> Jackson-databind의 ObjectMapper 클래스를 사용하여 해석
		log.debug("message = {}", message.getPayload());
		
		//수신한 내용 해석
		ObjectMapper mapper = new ObjectMapper();
		//mapper.readValue(무엇을, 어떤 형태로);
		JsonMessageVO vo = mapper.readValue(
							message.getPayload(), JsonMessageVO.class);
		vo.setTime(System.currentTimeMillis());
		log.debug("vo = {}", vo);
		
		//보낼 메세지 생성
		String json = mapper.writeValueAsString(vo);
		TextMessage newMessage = new TextMessage(json);
		
		//생성한 메세지 전송
		for(WebSocketSession user : users) {
			user.sendMessage(newMessage);
		}
	}
	
}








