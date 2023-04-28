<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="password" method="post">
<div class="container-400">
	<div class="row center">
		<h2>비밀번호 변경</h2>
	</div>
	<div class="row">
		<label>현재 비밀번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" name="currentPw" required class="form-input w-100">
	</div>
	<div class="row">
		<label>변경 비밀번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" name="changePw" required class="form-input w-100">
	</div>
	<div class="row">
		<button type="submit" class="form-btn positive w-100">변경</button>
	</div>
	
	<!-- 오류가 발생한 경우 보여줄 메세지 -->
	<c:if test="${param.mode == 'error'}">
	<div class="row center">
		<h2>비밀번호가 일치하지 않습니다</h2>
	</div>
	</c:if>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




