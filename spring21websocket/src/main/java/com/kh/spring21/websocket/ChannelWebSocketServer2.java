package com.kh.spring21.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring21.vo.MemberMessageVO;
import com.kh.spring21.vo.RoomVO;
import com.kh.spring21.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

//목표 : 회원들이 모여있는 채팅방(RoomVO)을 생성하여 적용
@Slf4j
@Service
public class ChannelWebSocketServer2 extends TextWebSocketHandler {
	
	//사용자 저장소
	private RoomVO room = new RoomVO();
	
	//메세지 해석기
	private ObjectMapper mapper = new ObjectMapper(); 
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//사용자 정보를 객체로 변환하여 저장
		UserVO user = new UserVO(session);
		room.enter(user);
		log.debug("사용자 입장. 현재 {}명", room.size());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//삭제의 조건
		//[1] 자료형이 일치해야 함(UserVO)
		//[2] 비교기준이 설정되어있어야 함(hashCode, equals)
		UserVO user = new UserVO(session);
		room.leave(user);
		log.debug("사용자 퇴장. 현재 {}명", room.size());
	}
	
	/**
		웹소켓 세션은 HTTP세션과는 다른 저장소이며 생성되고 관리되는 시점이 다름
		
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//회원 정보를 생성
		UserVO user = new UserVO(session);
		
		//비회원 차단
		if(user.isMember() == false) return;
		
		//방에 없으면 차단하는 코드를 추가
		if(room.contains(user) == false) return;
		
		//메세지 해석 및 신규 메세지 생성 & 전송
		MemberMessageVO msg = mapper.readValue(
				message.getPayload(), MemberMessageVO.class);
		msg.setTime(System.currentTimeMillis());
		msg.setMemberId(user.getMemberId());
		msg.setMemberLevel(user.getMemberLevel());
		
		String json = mapper.writeValueAsString(msg);
		TextMessage jsonMessage = new TextMessage(json);
		
		//방 전체 사용자에게 broadcast
		room.broadcast(jsonMessage);
	}
	
}
