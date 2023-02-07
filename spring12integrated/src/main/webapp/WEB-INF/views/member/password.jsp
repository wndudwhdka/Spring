<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>비밀번호 변경</h1>

<form action="password" method="post">
현재 비밀번호 : <input type="text" name="currentPw"required><br><br>
변경 비밀번호 : <input type="text" name="changePw"required><br><br>
<button>변경</button>
</form>

<!-- 오류가 발생한 경우 보여줄 메세지-->
<c:if test="${param.mode == 'error'}">
<h2>비밀번호가 일치하지 않습니다</h2>
</c:if>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>