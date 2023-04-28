<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>

<form action="edit" method="post">
<input type="hidden" name="memberId" value="${memberDto.memberId}">

<div class="container-600">
	<div class="row center">
		<h2>회원 정보 변경</h2>
	</div>
	<div class="row">
		<label>닉네임</label>
		<input type="text" name="memberNick" required value="${memberDto.memberNick}" class="form-input w-100">
	</div>
	<div class="row">
		<label>전화번호</label>
		<input type="tel" name="memberTel" required value="${memberDto.memberTel}" class="form-input w-100">
	</div>
	<div class="row">
		<label class="form-label w-100">생년월일</label>
		<input type="date" name="memberBirth" required value="${memberDto.memberBirth}" class="form-input">
	</div>
	<div class="row">
		<label>이메일</label>
		<input type="email" name="memberEmail" value="${memberDto.memberEmail}" class="form-input w-100">
	</div>
	<div class="row">
		<label class="form-label w-100">주소</label>
		<input type="text" name="memberPost" size="6" maxlength="6" 
					placeholder="우편번호" value="${memberDto.memberPost}" class="form-input">
		<button type="button" class="form-btn neutral">우편번호 찾기</button>
	</div>
	<div class="row">
		<input type="text" name="memberBasicAddr"
					placeholder="기본주소" value="${memberDto.memberBasicAddr}" class="form-input w-100">
	</div>
	<div class="row">
		<input type="text" name="memberDetailAddr" 
					placeholder="상세주소" value="${memberDto.memberDetailAddr}" class="form-input w-100">
	</div>
	
	<div class="row">
		<label class="form-label w-100">레벨</label>
		<c:choose>
			<c:when test="${memberDto.memberLevel == '일반회원'}">
				<select name="memberLevel" class="form-input">
					<option>준회원</option>
					<option selected>일반회원</option>
					<option>우수회원</option>
				</select>
			</c:when>
			<c:when test="${memberDto.memberLevel == '우수회원'}">
				<select name="memberLevel" class="form-input">
					<option>준회원</option>
					<option>일반회원</option>
					<option selected>우수회원</option>
				</select>
			</c:when>
			<c:otherwise>
				<select name="memberLevel" class="form-input">
					<option selected>준회원</option>
					<option>일반회원</option>
					<option>우수회원</option>
				</select>
			</c:otherwise>
		</c:choose>
		
		(기존 : ${memberDto.memberLevel})
	</div>
	
	<div class="row">
		<label class="form-label w-100">포인트</label>
		<input type="text" name="memberPoint" value="${memberDto.memberPoint}" required class="form-input">
				(기존 : ${memberDto.memberPoint} point)
	</div>
	
	<div class="row right">
		<a href="list" class="form-btn neutral">목록</a>
		<button type="submit" class="form-btn positive">수정</button>
	</div>
</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>