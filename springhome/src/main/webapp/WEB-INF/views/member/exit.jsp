<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="exit" method="post" autocomplete="off">
<div class="container-500">
	<div class="row center">
		<h2>비밀번호 확인<i class="fa-solid fa-asterisk"></i></h2>
	</div>
	<div class="row center">
		탈퇴하시려면 비밀번호를 입력하세요		
	</div>
	
	<div class="row">
		<input type="password" name="memberPw" placeholder="현재 비밀번호" required class="form-input w-100">
	</div>
	
	<div class="row center">
		<button class="form-btn negative">탈퇴</button>
	</div>
	
	<c:if test="${param.mode == 'error'}">
	<div class="row center">
		<h3>비밀번호가 일치하지 않습니다</h3>
	</div>
	</c:if>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




