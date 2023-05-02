package com.kh.spring21.vo;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;


// 한 개의 채팅방에 필요한 정보와 기능을 가진 클래스
public class RoomVO {
	
	// 채팅방의 사용자를 저장할 저장소 
	private Set<UserVO> users = new CopyOnWriteArraySet<>();
	
	// 입장 기능
	public void enter(UserVO user) {
		users.add(user);
	}
	
	// 퇴장 기능
	public void leave(UserVO user) {
		users.remove(user);
	}
	
	// 인원수 반환 기능
	public int size() {
		return users.size();
	}

	// 사용자 유무 확인 기능 (equals / hashCode 반드시 정의)
	public boolean contains(UserVO user) {
		return users.contains(user); 
	}

	public UserVO[] getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void broadcast(TextMessage jsonMessage) throws IOException {
		for(UserVO user : users) {
			user.send(jsonMessage);
		}
		
	}	
	
}