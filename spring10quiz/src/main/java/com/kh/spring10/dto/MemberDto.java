package com.kh.spring10.dto;

import java.sql.Date;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDto {
	//field는 카멜 케이스로 구현
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
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberPw=" + memberPw + ", memberNick=" + memberNick
				+ ", memberTel=" + memberTel + ", memberEmail=" + memberEmail + ", memberBirth=" + memberBirth
				+ ", memberPost=" + memberPost + ", memberBasicAddr=" + memberBasicAddr + ", memberDetailAddr="
				+ memberDetailAddr + ", memberLevel=" + memberLevel + ", memberPoint=" + memberPoint + ", memberJoin="
				+ memberJoin + ", memberLogin=" + memberLogin + "]";
	}
	public MemberDto() {
		super();
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberPost() {
		return memberPost;
	}
	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}
	public String getMemberBasicAddr() {
		return memberBasicAddr;
	}
	public void setMemberBasicAddr(String memberBasicAddr) {
		this.memberBasicAddr = memberBasicAddr;
	}
	public String getMemberDetailAddr() {
		return memberDetailAddr;
	}
	public void setMemberDetailAddr(String memberDetailAddr) {
		this.memberDetailAddr = memberDetailAddr;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public Date getMemberJoin() {
		return memberJoin;
	}
	public void setMemberJoin(Date memberJoin) {
		this.memberJoin = memberJoin;
	}
	public Date getMemberLogin() {
		return memberLogin;
	}
	public void setMemberLogin(Date memberLogin) {
		this.memberLogin = memberLogin;
	}
}