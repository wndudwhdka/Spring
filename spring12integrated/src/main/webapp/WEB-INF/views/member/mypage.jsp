<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 프로필 이미지 유무에 따라 이미지를 표시 -->
<div class="container-800">
	<div class="left">
	<h1>마이페이지</h1>
	</div>
	<div >
	<c:choose>
		<c:when test="${profile !=null}">
			<img width="200"height="200"src="/static/image/user.jpg">
<%-- 		<img width="200"height="200"src="/attachment/download?attachmentNo=${profile.attachmentNo}"> --%>
		</c:when>
		<c:otherwise>
			<img width="200"height="200"src="/static/image/user.jpg">
		</c:otherwise>
	</c:choose>
	</div>
	<div class="row right">
		<h1>나의 정보입니다. </h1>
		<table class="table table-border">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${memberDto.memberId}</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>${memberDto.memberNick}</td>
				</tr>
				<tr>
					<th>레벨</th>
					<td>${memberDto.memberLevel}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${memberDto.getMemberTel()}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${memberDto.getMemberEmail() }</td>
				</tr>
				<tr>
					<th>생일</th>
					<td>${memberDto.getMemberBirth() }</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${memberDto.getMemberPost()}</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>
					<fmt:formatDate value="${memberDto.memberJoin}"
						pattern="y년 M월 d일 E a h시 m분 s초"></fmt:formatDate>
					</td>
				</tr>
				<tr>
					<th>최종 로그인 일시</th>
					<td><fmt:formatDate value="${memberDto.memberLogin}"
						pattern="y년 M월 d일 E a h시 m분 s초"></fmt:formatDate></td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- 회원 관리 메뉴 -->
	<div class="row right">
		<a class="right form-btn form-btn neutral" href="password">비밀번호 변경</a>
		<a class="right form-btn form-btn neutral" href="edit">개인정보 변경</a>
		<a class="right form-btn form-btn neutral" href="exit">회원 탈퇴</a>
	</div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
