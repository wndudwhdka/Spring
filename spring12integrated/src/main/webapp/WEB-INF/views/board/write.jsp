<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<c:choose>
	<c:when test="${boardParent==null}">
		<h1>새글 작성</h1>
	</c:when>
	<c:otherwise>
		<h1>답글 작성</h1>
	</c:otherwise>
</c:choose> 


<form action="write" method="post">
	<%-- 답글일 때는 정보가 하나 더 있어야한다.(boardParent) --%>
	<c:if test="${boardParent!=null }">
		<input type="hidden" name="boardParent"value="${boardParent}">
	</c:if>
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

