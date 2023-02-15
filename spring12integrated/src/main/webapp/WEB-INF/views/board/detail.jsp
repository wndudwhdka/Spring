<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>    

<h1>${boardDto.boardNo}번 게시글</h1>

<table border="1" width="500">
	<tbody>
		<tr>
			<td>
				<h2>${boardDto.boardTitle}</h2>
			</td>
		</tr>
		<tr>
			<td>
				${boardDto.boardWriter}
			</td>
		</tr>
		<tr>
			<td>
				<fmt:formatDate value="${boardDto.boardTime}" 
										pattern="y년 M월 d일 H시 m분 s초"/>
				조회 ${boardDto.boardRead}
			</td>
		</tr>
		<tr height="150" valign="top">
			<td>${boardDto.boardContent}</td>
		</tr>
		<tr>
			<td>
				좋아요 ${boardDto.boardLike}
				댓글 ${boardDto.boardReply}
			</td>
		</tr>
		<tr>
			<td>미래의 댓글목록</td>
		</tr>
		<tr>
			<td>미래의 댓글작성창</td>
		</tr>
		<tr>
			<td>
				<a href="/board/write">글쓰기</a>
				<a href="/board/write/boardParent=${boardDto.boardParent}">답글쓰기</a>
				
				<c:if test="${owner}">
				<!-- 내가 작성한 글이라면 수정과 삭제 메뉴를 출력 -->
				<a href="/board/edit?boardNo=${boardDto.boardNo}">수정</a>
				</c:if>
				
				<c:if test="${owner || admin}">
				<!-- 파라미터 방식일 경우의 링크 -->
				<a href="/board/delete?boardNo=${boardDto.boardNo}">삭제</a>
				<!-- 경로 변수 방식일 경우의 링크 -->
<%-- 				<a href="/board/delete/${boardDto.boardNo}">삭제</a> --%>
				</c:if>
				<a href="/board/list">목록보기</a>
			</td>
		</tr>
	</tbody>
</table>

<%-- (+추가) 오늘 읽은 글(memory) 목록을 출력 --%>
<c:forEach var="number" items="${sessionScope.memory}">
	${number}<br>
</c:forEach>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>