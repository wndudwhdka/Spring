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
    <link rel="stylesheet" type="text/css" href="/static/css/load.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/css/layout.css">
    <link rel="stylesheet" type="text/css" href="/static/css/commons.css">
    <link rel="stylesheet" type="text/css" href="/static/css/test.css">
    <style>
	   	
	   	/*
	    홈페이지 메인 레이아웃
	*/
	
	/* main을 제어해서 홈페이지 전체 폭과 정렬을 제어 */
	main {
	    width:1000px;
	    margin:0 auto;
	}
	/* 내부 영역에 기본 여백을 부여 */
	header, footer {
	    padding: 1em;
	}
	/* section은 다단 레이아웃이 가능하도록 배치 */
	section {
	    display: flex;
	}
	aside {
	    width: 150px;
	}
	article {
	    flex-grow: 1;
	    padding: 1em;
	    min-height: 450px;
	}
    </style>
</head>
<body>
    <!--
        홈페이지를 만들 때 시맨틱 태그(semantic tag)를 사용하여 의미있게 구현
    -->
    <main>
        <header>
            <div class="flex-box">
                <div class="w-25">
                    <img src="https://via.placeholder.com/150x60?text=LOGO" alt="로고">
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
                <li><a href="/pocketmon/list">포켓몬관리</a></li>
                <li><a href="/subject/list">과목관리</a></li>
                <li><a href="/student/list">학생관리</a></li>
                <li><a href="/board/list">게시판</a></li>

                <li class="right-menu">
                    <a>회원메뉴</a>
                    <ul>
                    	<!-- 로그아웃 상태 -->
                    	<c:if test="${memberId == null}">
                        <li><a href="/member/login">로그인</a></li>
                        <li><a href="/member/join">회원가입</a></li>
                    	</c:if>
                    	<!-- 로그인 상태 -->
                    	<c:if test="${memberId != null}">
                        <li><a href="/member/mypage">내정보</a></li>
                        <li><a href="/member/logout">로그아웃</a></li>
                    	</c:if>
                    	<!-- 관리자 -->
                    	<c:if test="${memberLevel == '관리자'}">
                        <li><a href="/admin/home">관리메뉴</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </nav>
        <section>
            <aside style="border-right:1px solid gray;">
                <!-- 관리 메뉴는 수직으로 배치(List-Group 형태) -->
                <div class="flex-box flex-vertical">
                    <div class="p-10">
                        <h2>관리자 메뉴</h2>
                    </div>
                    <div class="p-10"><a href="/admin/member/list" class="link">회원 현황</a></div>
                    <div class="p-10"><a href="/admin/stat/pocketmon" class="link">포켓몬 현황</a></div>
                    <div class="p-10"><a href="/admin/stat/subject" class="link">과목 개설 현황</a></div>
                    <div class="p-10"><a href="/admin/stat/member" class="link">회원 통계</a></div>
                </div>
            </aside>
            <article>