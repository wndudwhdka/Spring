<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="find" method="post" autocomplete="off">
<div class="container-500">
	<div class="row center">
		<h2>아이디 찾기</h2>
	</div>
	<div class="row">
		<label>닉네임<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="memberNick" required>
	</div>
	<div class="row">
		<label>전화번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="tel" name="memberTel" required>
	</div>
	<div class="row">
		<label>생년월일<i class="fa-solid fa-asterisk"></i></label>
		<input type="date" name="memberBirth" required>
	</div>
	<div class="row center">
		<button class="form-btn positive w-100">찾기</button>
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>