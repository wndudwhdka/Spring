<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-500">
	<div class="row center">
		<h2>아이디 찾기 결과</h2>
	</div>
	<div class="row center">
		찾으시는 아이디는 ${requestScope.findId} 입니다.
	</div>
	<div class="row center">
		<h2><a href="login" class="link">로그인 페이지로</a></h2>
	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>