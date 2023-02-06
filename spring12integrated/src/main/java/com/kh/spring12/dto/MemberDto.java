package com.kh.spring12.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDto {
	private String memberId, memberPw, memberNick,memberTel,memberEmail,memberBirth,memberPost,memberBasicAddr,memberDetailAddr,memberLevel;
	private int memberPoint;
	private Date memberJoin, memberLogin; 
}
