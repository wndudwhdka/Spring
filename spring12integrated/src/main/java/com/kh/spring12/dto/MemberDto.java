package com.kh.spring12.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDto {
	private String memberId;
	private String memberPw;
	private String memberNick;
	private String memberTel;
	private String memberEmail;
	private String memberBirth;
	private String memberPost;
	private String memberBasicAddr;
	private String memberDetailAddr;
	private String memberLevel;
	private int memberPoint;
	private Date memberJoin;
	private Date memberLogin;
}
