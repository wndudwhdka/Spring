<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
	jsp가 제공하는 설정 중에 include라는 설정이 있다
	이를 사용하여 상단, 하단 템플릿 페이지를 불러온다. 
--%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 중단 -->
<h1>환영합니다! 홈페이지입니다.</h1>
<div class="container-500">
	
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>