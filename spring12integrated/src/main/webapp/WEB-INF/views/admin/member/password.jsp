<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>

<div class="container-600">
	<div class="row center">
		<h2>비밀번호 설정 완료</h2>
	</div>
	<div class="row center">
		${memberPw} 로 변경되었습니다
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>