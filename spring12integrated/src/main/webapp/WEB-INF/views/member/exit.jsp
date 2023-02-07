<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>삭제 확인 페이지</h1>
<h2>삭제를 위해 비밀번호를 입력하세요</h2>

<form action="exit" method="post">
	<input type="password" name="memberPw"required placeholder="현재 비밀번호" required> 
	<button>삭제완료</button>
</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>