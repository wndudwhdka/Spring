package com.kh.spring18.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

//회원 복합 검색용 VO
@Data
public class MemberComplexSearchVO {
	private String memberId;
	private String memberNick;
	private String memberEmail;
	private String memberBirth;
	private String memberTel;
	private String memberAddress;
	
	private List<String> memberLevelList;
	
	private Integer minPoint;
	private Integer maxPoint;
	
	private String beginJoindate;
	private String endJoindate;
	private Integer searchLoginDays;
	
	private List<String> orderList;
	
	//orderList에 들어있는 빈 문자열(empty string)을 제거
	public void refreshOrderList() {
		if(this.orderList == null) return;
		
		List<String> list = new ArrayList<>();
		for(String str : orderList) {
			if(str.length() > 0) {
				list.add(str);
			}
		}
		this.orderList = list;
	}
}








