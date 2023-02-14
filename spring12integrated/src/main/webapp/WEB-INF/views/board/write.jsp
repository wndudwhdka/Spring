<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>게시글 작성</h1>

<form action="write" method="post">
	말머리 : 
	<select name="boardHead">
		<!-- 없음을 선택하면 값이 비어서 전송되므로 DB에 null로 들어감 -->
		<option value="">없음</option>
		<c:if test="${memberLevel == '관리자'}">
			<option>공지</option>
		</c:if>
		<option>유머</option>
		<option>정보</option>
	</select>
	<br><br>
	제목 : <input type="text" name="boardTitle" required><br><br>
	<textarea name="boardContent" required rows="10" cols="60"></textarea> <br><br>
	<button>등록</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

