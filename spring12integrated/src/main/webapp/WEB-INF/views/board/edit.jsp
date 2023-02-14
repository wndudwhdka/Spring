<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>${boardDto.boardNo}번 게시글 수정</h1>

<form action="edit" method="post">
	<input type="hidden" name="boardNo" value="${boardDto.boardNo}">
	
	말머리 : 
	<c:choose>
		<c:when test="${boardDto.boardHead == '정보'}">
			<select name="boardHead">
				<option value="">없음</option>
				<c:if test="${memberLevel == '관리자'}">
				<option>공지</option>
				</c:if>
				<option>유머</option>
				<option selected>정보</option>
			</select>
		</c:when>
		<c:when test="${boardDto.boardHead == '유머'}">
			<select name="boardHead">
				<option value="">없음</option>
				<c:if test="${memberLevel == '관리자'}">
				<option>공지</option>
				</c:if>
				<option selected>유머</option>
				<option>정보</option>
			</select>
		</c:when>
		<c:when test="${boardDto.boardHead == '공지'}">
			<select name="boardHead">
				<option value="">없음</option>
				<c:if test="${memberLevel == '관리자'}">
				<option selected>공지</option>
				</c:if>
				<option>유머</option>
				<option>정보</option>
			</select>
		</c:when>
		<c:otherwise>
			<select name="boardHead">
				<option value="" selected>없음</option>
				<c:if test="${memberLevel == '관리자'}">
				<option>공지</option>
				</c:if>
				<option>유머</option>
				<option>정보</option>
			</select>
		</c:otherwise>
	</c:choose>	
 
	<br><br>
	제목 : <input type="text" name="boardTitle" value="${boardDto.boardTitle}" required>
	<br><br>
	<!-- textarea는 value가 없다 -->
	<textarea name="boardContent" required 
		rows="10" cols="60">${boardDto.boardContent}</textarea>
	<br><br>
	
	<button>변경</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>