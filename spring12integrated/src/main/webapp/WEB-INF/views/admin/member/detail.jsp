<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>

<div class="container-800">
	<div class="row">
		<h2>${memberDto.memberNick} 님의 개인 프로필</h2>
	</div>
	<div class="row flex-box">
		<div class="w-30">
			<div class="row">
				<!-- 프로필 이미지 유무에 따라 이미지를 표시 -->
				<c:choose>
					<c:when test="${profile != null}">
						<img width="200" height="200" src="/attachment/download?attachmentNo=${profile.attachmentNo}">
					</c:when>
					<c:otherwise>
						<img width="200" height="200" src="/static/image/user.png">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="row center">
				<h2><a href="edit?memberId=${memberDto.memberId}" class="link">개인정보 변경</a></h2>
			</div>
			<div class="row center">
				<h2><a href="password?memberId=${memberDto.memberId}" class="link">일회용 비밀번호 설정</a></h2>
			</div>
			<div class="row center">
				<h2><a href="exit?memberId=${memberDto.memberId}" class="link">회원 강제 탈퇴</a></h2>
			</div>
		</div>
		<div class="w-70">
			<table class="table table-slit">
				<tr>
					<th class="w-25">아이디</th>
					<td>${memberDto.memberId}</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>${memberDto.memberNick}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${memberDto.memberTel}</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${memberDto.memberBirth}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${memberDto.memberEmail}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						[${memberDto.memberPost}] 
						${memberDto.memberBasicAddr}
						${memberDto.memberDetailAddr}
					</td>
				</tr>
				<tr>
					<th>레벨</th>
					<td>${memberDto.memberLevel}</td>
				</tr>
				<tr>
					<th>포인트</th>
					<td>${memberDto.memberPoint} point</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>
						<fmt:formatDate value="${memberDto.memberJoin}"
								pattern="y년 M월 d일 E a h시 m분 s초"/>
					</td>
				</tr>
				<tr>
					<th>최종 로그인</th>
					<td>
						<fmt:formatDate value="${memberDto.memberLogin}"
								pattern="y년 M월 d일 E a h시 m분 s초"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="row">
		<!-- 회원 관리 메뉴 -->
		<a href="list" class="form-btn neutral w-100">목록 보기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>









