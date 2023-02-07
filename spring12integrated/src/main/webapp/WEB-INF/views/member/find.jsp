<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>아이디 찾기 페이지</h1>


<form action="find" method="post">
	<input type="text" name="memberNick">
	<input type="text" name="memberTel">
	<input type="text" name="memberBirth">
	<button>찾기	</button>
</form>

<!-- 테이블부터 th/td 사이에는 어떠한 태그도 적을 수 없다.  -->
<c:if test="${param.mode == 'error'}">
	<h2>비밀번호가 일치하지 않습니다.</h2>
</c:if>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
