<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>홈페이지 레이아웃</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/load.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/layout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/commons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/test.css">
    
    <!-- favicon 설정 -->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.ico">
    
    <style>
        .fa-asterisk {
        	color:red;
        	margin-left: 2px;
        }
    </style>
    
    <!-- 
    	Javascript에서 절대경로를 사용하기 위한 꼼수
    	- JS는 절대경로란 개념이 없으므로 JSP의 EL의 도움을 받아야 함
    	- <script>는 분할해서 작성해도 결국 이어지는 특징을 활용
    	- 모든 <script>의 가장 위에 다음과 같이 변수를 하나 선언
    	- const로 변수를 선언하면 자바의 final과 같이 불변 처리가 됨
     -->
    <script>
    	const contextPath = "${pageContext.request.contextPath}";
    </script>
     
    
    <!-- 링크 확인창 출력을 위한 CDN -->
    <script src="https://cdn.jsdelivr.net/gh/hiphop5782/confirm-link@latest/confirm-link.min.js"></script>
    
    <!-- jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
</head>
<body>
    <!--
        홈페이지를 만들 때 시맨틱 태그(semantic tag)를 사용하여 의미있게 구현
    -->
    <main>
        <header>
            <div class="flex-box">
                <div class="w-25">
                    <img src="${pageContext.request.contextPath}/static/image/kh_logo.jpg" alt="로고">
                </div>
                <div class="w-75">
                    <h1>내가 만든 첫 번째 홈페이지</h1>
                </div>
            </div>
        </header>
        <nav>
        	<!-- 메뉴를 상태에 따라 다르게 나오도록 처리 -->
        	
            <ul class="menu">
                <li><a href="/">홈</a></li>
                <li>
                	<a>데이터</a>
                	<ul>
		                <li>
		                	<a>포켓몬관리</a>
		                	<ul>
		                		<li><a href="${pageContext.request.contextPath}/pocketmon/list">목록보기</a></li>
		                		<li><a href="${pageContext.request.contextPath}/pocketmon/insert">등록하기</a></li>
		                	</ul>
		                </li>
		                <li>
		                	<a>과목관리</a>
		                	<ul>
		                		<li><a href="${pageContext.request.contextPath}/subject/list">목록보기</a></li>
		                		<li><a href="${pageContext.request.contextPath}/subject/insert">등록하기</a></li>
		                	</ul>
		                </li>
		                <li>
		                	<a>학생관리</a>
		                	<ul>
		                		<li><a href="${pageContext.request.contextPath}/student/list">목록보기</a></li>
		                		<li><a href="${pageContext.request.contextPath}/student/insert">등록하기</a>
		                	</ul>
		                </li>
                	</ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>

                <li class="right-menu">
                    <a>회원메뉴</a>
                    <ul>
                    	<!-- 로그아웃 상태 -->
                    	<c:if test="${memberId == null}">
                        <li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
                        <li><a href="${pageContext.request.contextPath}/member/join">회원가입</a></li>
                    	</c:if>
                    	<!-- 로그인 상태 -->
                    	<c:if test="${memberId != null}">
                        <li><a href="${pageContext.request.contextPath}/member/mypage">내정보</a></li>
                        <li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
                    	</c:if>
                    	<!-- 관리자 -->
                    	<c:if test="${memberLevel == '관리자'}">
                        <li><a href="${pageContext.request.contextPath}/admin/home">관리메뉴</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </nav>
        <section>
            <article>