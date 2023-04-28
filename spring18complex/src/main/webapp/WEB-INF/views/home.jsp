<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>회원 복합 검색</h1>

<%-- <div>${vo}</div> --%>

<!-- 검색창 --> 
<form method="get">
	아이디 : <input type="text" name="memberId" value="${vo.memberId}"><br><br>
	닉네임 : <input type="text" name="memberNick" value="${vo.memberNick}"><br><br>
	전화번호 : <input type="tel" name="memberTel" value="${vo.memberTel}"><br><br>
	이메일 : <input type="email" name="memberEmail" value="${vo.memberEmail}"><br><br>
	생년월일 : <input type="text" name="memberBirth" value="${vo.memberBirth}"><br><br>
	주소 : <input type="text" name="memberAddress" value="${vo.memberAddress}"><br><br>
	등급 : 
	<input type="checkbox" name="memberLevelList" value="일반회원" ${vo.memberLevelList.contains('일반회원') ? 'checked' : ''}> 일반회원
	<input type="checkbox" name="memberLevelList" value="우수회원" ${vo.memberLevelList.contains('우수회원') ? 'checked' : ''}> 우수회원
	<input type="checkbox" name="memberLevelList" value="관리자" ${vo.memberLevelList.contains('관리자') ? 'checked' : ''}> 관리자
	<br><br>
	포인트 : 
	<input type="number" name="minPoint" value="${vo.minPoint}"> 이상
	<input type="number" name="maxPoint" value="${vo.maxPoint}"> 이하
	<br><br>
	가입일 : 
	<input type="date" name="beginJoindate" value="${vo.beginJoindate}"> 부터
	<input type="date" name="endJoindate" value="${vo.endJoindate}"> 까지
	<br><br>
	로그인 : 
	<select name="searchLoginDays">
		<option value="">선택하세요</option>
		<option value="7" ${vo.searchLoginDays == 7 ? 'selected' : ''}>최근 7일</option>
		<option value="30" ${vo.searchLoginDays == 30 ? 'selected' : ''}>최근 1개월</option>
		<option value="365" ${vo.searchLoginDays == 365 ? 'selected' : ''}>최근 1년</option>
	</select>
	<br><br>
	
	<!-- 정렬 옵션 -->
	1차 정렬 : 
	<select name="orderList">
		<option value="">선택하세요</option>
		<option value="member_id asc">아이디순</option>
		<option value="member_join desc">최근 가입순</option>
		<option value="member_login desc">최근 로그인 순</option>
		<option value="member_point desc">포인트 많은순</option>
	</select>
	
	<br><br>
	
	2차 정렬 : 
	<select name="orderList">
		<option value="">선택하세요</option>
		<option value="member_id asc">아이디순</option>
		<option value="member_join desc">최근 가입순</option>
		<option value="member_login desc">최근 로그인 순</option>
		<option value="member_point desc">포인트 많은순</option>
	</select>
	
	<br><br>
	<button type="submit">검색</button>
</form>

<hr>

<!-- 결과화면 -->
<c:forEach var="memberDto" items="${list}">
	<div>${memberDto}</div>
</c:forEach>