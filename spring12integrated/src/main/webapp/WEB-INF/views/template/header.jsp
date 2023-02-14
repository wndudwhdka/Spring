<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 만든 홈페이지</title>
</head>
<body>
	<%-- 
		메뉴는 로그인 여부에 따라 다르게 출력
		- 로그인 여부는 세션에 memberId가 있는 지 여부로 판정
	 --%>
	<c:choose>
		<c:when test="${memberId!=null}">
		<!-- 로그인 상태 -->
		<a href="/">홈으로</a>
		<a href="/member/logout">로그아웃</a>
		</c:when>
		<c:otherwise>
		<!-- 로그아웃 상태 -->
		<a href="/">홈으로</a>
		<a href="/member/join">회원가입</a>
		<a href="/member/login">로그인</a>
		</c:otherwise>
	</c:choose>
	<a href="/pocketmon/list">포켓몬 관리</a>
	<a href="/subject/list">과목관리</a>
	<a href="/student/list">학생관리</a>
	<a href="/member/mypage">내 정보</a>
	<a href="/board/list">게시판</a>
	
	<c:if test="${memberLevel=='관리자'}"	>
		<a href="/admin/home">관리자 모드</a>
	</c:if>