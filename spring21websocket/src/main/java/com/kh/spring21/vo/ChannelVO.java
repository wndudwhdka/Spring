package com.kh.spring21.vo;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;

import lombok.extern.slf4j.Slf4j;

//여러 개의 방이 모인 채널 클래스
@Slf4j
public class ChannelVO {
	
	// 여러 개의 방을 관리할 저장소
	Map<String, RoomVO> rooms = 
			Collections.synchronizedMap(new HashMap<>());
	
	// 필요한 기능
	// - 방생성/제거/확인
	public void createRoom(String roomName) {
		if(containsRoom(roomName)) return;
		rooms.put(roomName,new RoomVO());
	}
	
	public void deleteRoom(String roomName) {
		rooms.remove(roomName);
	}
	
	public boolean containsRoom(String roomName) {
		return rooms.containsKey(roomName);
	}
	
	// - 사용자를 방에 입장/퇴장
	public void join(UserVO user, String roomName) {
		createRoom(roomName); // 방 생성 (있으면 안만들어짐)
		
		RoomVO room = rooms.get(roomName);
		room.enter(user);
		
		log.debug("{} 님이 {} 방으로 참여하였습니다", user.getMemberId(), roomName);
	}
	
	public void exit(UserVO user,String roomName) {
		if(containsRoom(roomName) == false) return;
		
		RoomVO room = rooms.get(roomName);
		room.leave(user);
		log.debug("{} 님이 {} 방에서 퇴장하셨습니다", user.getMemberId(), roomName);
	}

	// - 방에 메세지를 전송하는 기능(그룹 세팅)
	public void broadcastRoom(TextMessage jsonMessage, String roomName) throws IOException {
		if(containsRoom(roomName) == false) return;
		
		RoomVO room = rooms.get(roomName);
		room.broadcast(jsonMessage);
	}
	
	// - 사용자가 존재하는 방의 이름을 찾는 기능
	// -> 모든 방을 뒤져서 나오면 해당 방 이름을 반환 
	public String findUser(UserVO user) {
		for(String roomName : rooms.keySet()) { // 모든 방의 이름을 꺼내서 
			RoomVO room = rooms.get(roomName); // 방 객체를 추출하고
			if(room.contains(user)) { // 해당 방 객체에 사용자가 있다면
				return roomName; // 방 이름을 반환 
			}
		}
		return null; // 없네
	}
	
	// - 사용자를 방에서 방으로 이동시키는 기능
	public void moveUser(UserVO user,String roomName) {
		String beforeRoomName = findUser(user); // 사용자 위치를 찾아라
		exit(user,beforeRoomName); // 나가!(이전 방에서)
		join(user,roomName); // 들어가   
	}
	
	// - 방 개수 
	// - 전체 방에 메세지를 전송하는 기능(공지)
	
}