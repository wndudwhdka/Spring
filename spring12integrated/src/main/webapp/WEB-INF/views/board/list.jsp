<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<h1>게시글 목록</h1>

<table border="1" width="700">
	<thead>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>글제목</th>
			<th>조회수</th>
			<th>좋아요수</th>
			<th>댓글수</th>			
		</tr>
	</thead>
	<tbody>
	
	<c:forEach var="boardDto" items="${list}">
		<tr align="center">
			<td width="10%">${boardDto.boardNo}</td>
			<td width="20%">${boardDto.boardWriter}</td>
			<td><a href="detail?no=${boardDto.boardNo}">${boardDto.boardTitle}</a></td>
			<td width="10%">${boardDto.boardRead}</td>
			<td width="10%">${boardDto.boardLike}</td>
			<td width="10%">${boardDto.boardReply}</td>	
		</tr>
	</c:forEach>
	</tbody>
</table>

<c:forEach var="i" begin="1" end="${totalPage}" step="1">
	<c:choose>
		<c:when test="${page ==i}">
			${i}
		</c:when>
		<c:otherwise>
			<a href="list?page=${i}">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

