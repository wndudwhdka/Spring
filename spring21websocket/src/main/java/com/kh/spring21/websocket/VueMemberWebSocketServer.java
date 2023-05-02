package com.kh.spring21.websocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring21.vo.MemberMessageVO;

import lombok.extern.slf4j.Slf4j;

//목표 : 비회원은 보기만, 회원은 전송도 가능한 채팅 서버
//(추가) 아이디 또는 등급 정보를 채팅에서 활용할 수 있도록 구현
@Slf4j
@Service
public class VueMemberWebSocketServer extends TextWebSocketHandler {
	
	//사용자 저장소
	private Set<WebSocketSession> users = new CopyOnWriteArraySet<>();
	
	//메세지 해석기
	private ObjectMapper mapper = new ObjectMapper(); 
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		log.debug("사용자 입장. 현재 {}명", users.size());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session);
		log.debug("사용자 퇴장. 현재 {}명", users.size());
	}
	
	/**
		웹소켓 세션은 HTTP세션과는 다른 저장소이며 생성되고 관리되는 시점이 다름
		
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//log.debug("session = {}", session);
		//log.debug("http session = {}", session.getAttributes());
		
		//HTTP세션과 연동되었으므로 웹소켓 세션의 attributes를 조사하여 원하는 정보를 취득
		Map<String, Object> attr = session.getAttributes();
		String memberId = (String) attr.get("memberId");
		String memberLevel = (String) attr.get("memberLevel");
		log.debug("회원아이디 = {}, 회원등급 = {}", memberId, memberLevel);
		
		//비회원 차단
		if(memberId == null || memberLevel == null) return;
		
		//메세지 해석 및 신규 메세지 생성 & 전송
		MemberMessageVO vo = mapper.readValue(
				message.getPayload(), MemberMessageVO.class);
		vo.setTime(System.currentTimeMillis());
		vo.setMemberId(memberId);
		vo.setMemberLevel(memberLevel);
		
		String json = mapper.writeValueAsString(vo);
		TextMessage jsonMessage = new TextMessage(json);
		
		//broadcast
		for(WebSocketSession user : users) {
			user.sendMessage(jsonMessage);
		}
	}
	
}