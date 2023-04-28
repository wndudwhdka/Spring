<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 다음 우편 API 사용을 위한 CDN -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/gh/hiphop5782/daum-post-api@latest/find-address.min.js"></script> -->
<script src="/static/js/find-address.min.js"></script>

<!-- (주의) table부터 th/td 사이에는 어떠한 태그도 적을 수 없다 -->
<form action="edit" method="post">
<div class="container-600">
	<div class="row center">
		<h2>개인 정보 변경</h2>
	</div>
	<div class="row">
		<label>닉네임<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="memberNick" required class="form-input w-100" value="${memberDto.memberNick}">
	</div>
	<div class="row">
		<label>전화번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="tel" name="memberTel" required class="form-input w-100" placeholder="대시(-)를 제외하고 작성" value="${memberDto.memberTel}">
	</div>
	<div class="row">
		<label class="form-label w-100">생년월일<i class="fa-solid fa-asterisk"></i></label>
		<input type="date" name="memberBirth" required class="form-input" value="${memberDto.memberBirth}">
	</div>
	<div class="row">
		<label>이메일</label>
		<input type="email" name="memberEmail" class="form-input w-100" value="${memberDto.memberEmail}">
	</div>
	<div class="row">
		<label class="form-label w-100">주소</label>
		<input type="text" name="memberPost" class="form-input" placeholder="우편번호" readonly value="${memberDto.memberPost}">
		<button type="button" class="form-btn neutral find-address-btn">우편번호 찾기</button>
	</div>
	<div class="row">
		<input type="text" name="memberBasicAddr" class="form-input w-100" placeholder="기본주소" readonly value="${memberDto.memberBasicAddr}">
	</div>
	<div class="row">
		<input type="text" name="memberDetailAddr" class="form-input w-100" placeholder="상세주소" value="${memberDto.memberDetailAddr}">
	</div>
	<div class="row">
		<label class="form-label w-100">프로필 이미지</label>
		<input type="file" name="attach" class="form-input">
	</div>
	
	<div class="row">
		<label>비밀번호 확인<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" name="memberPw" required class="form-input w-100">
	</div>
	
	<div class="row">
		<button type="submit" class="form-btn positive w-100">수정하기</button>
	</div>
	
	<c:if test="${param.mode == 'error'}">
	<div class="row center">
		<h3 style="color:red;">비밀번호가 일치하지 않습니다</h3>
	</div>
	</c:if>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



