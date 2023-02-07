<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>삭제가 완료되었습니다.</h1>

<h2><a href="/">홈으로</a></h2>
<h2><a href="mypage">마이 페이지</a></h2>

<c:if test="${param.mode=='error'}">
	<h2>비밀번호가 일치하지 않습니다. </h2>
</c:if>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>