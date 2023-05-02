package com.kh.spring21.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring21.vo.ChannelReceiveVO;
import com.kh.spring21.vo.ChannelVO;
import com.kh.spring21.vo.MemberMessageVO;
import com.kh.spring21.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

//목표 : 최초 접속 시 대기실로 입장하고 채널을 선택해서 들어가도록 구현
@Slf4j
@Service
public class ChannelWebSocketServer5 extends TextWebSocketHandler {
	
	//사용자 저장소
	private ChannelVO channel = new ChannelVO();
	
	//메세지 해석기
	private ObjectMapper mapper = new ObjectMapper(); 
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//사용자 정보를 객체로 변환하여 저장
		UserVO user = new UserVO(session);
		String roomName = WebSocketConstant.WAITING_ROOM;
		channel.join(user, roomName);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//삭제의 조건
		//[1] 자료형이 일치해야 함(UserVO)
		//[2] 비교기준이 설정되어있어야 함(hashCode, equals)
		UserVO user = new UserVO(session);
		String roomName = channel.findUser(user);
		channel.exit(user, roomName);
	}
	
	/**
		handleTextMessage에서 수신되는 메세지는 두 가지 형태로 증가한다
		[1] 채팅 메세지 - { type : 1 , content : "메세지내용" }
		[2] 방 이동 메세지 - { type : 2 , room : "1" }
		
		각각의 형태에 맞게 다른 메세지를 사용해야 한다
		if(채널 이동 메세지라면) {
			메세지를 수신하여 채널 이동과 관련된 처리만 한다
		}
		else if(채팅 메세지라면) {
			메세지를 수신하여 채팅과 관련된 처리만 한다
		}
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//회원 정보를 생성
		UserVO user = new UserVO(session);
		
		//메세지를 수신(ChannelReceiveVO)
		//- 이 메세지의 type을 분석하여 작업에 맞는 처리를 수행
		ChannelReceiveVO receiveVO = 
				mapper.readValue(message.getPayload(), ChannelReceiveVO.class);
		log.debug("receiveVO = {}", receiveVO);
		
		//채팅메세지인 경우
		if(receiveVO.getType() == WebSocketConstant.CHAT) {
			//receiveVO.content에 채팅 메세지가 있다
			
			//비회원 차단 - 메세지만 전송을 하지 않음
			if(user.isMember() == false) return;
			
			String roomName = channel.findUser(user);//찾아
			if(roomName == null) return;//없으면 하지마
			
			//(옵션) 대기실인경우 메세지 전송이 불가
			if(roomName.equals(WebSocketConstant.WAITING_ROOM)) 
				return;
			
			//보낼 메세지 생성
			MemberMessageVO msg = new MemberMessageVO();
			msg.setContent(receiveVO.getContent());//전송내용
			msg.setTime(System.currentTimeMillis());//전송시각
			msg.setMemberId(user.getMemberId());//아이디
			msg.setMemberLevel(user.getMemberLevel());//등급
			
			//JSON 변환
			String jsonStr = mapper.writeValueAsString(msg);
			TextMessage jsonMessage = new TextMessage(jsonStr);
			
			channel.broadcastRoom(jsonMessage, roomName);//방에 메세지 보내
		}
		//입장메세지인 경우
		else if(receiveVO.getType() == WebSocketConstant.JOIN) {
			//receiveVO.room에 방 번호가 있다
			String roomName = receiveVO.getRoom();
			channel.moveUser(user, roomName);
		}
	}
	
}