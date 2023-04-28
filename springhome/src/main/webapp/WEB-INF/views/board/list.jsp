<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 제목 -->
<h1>자유 게시판</h1>
<h5>남을 비방하는 경우 예고 없이 삭제될 수 있습니다</h5>

<h3><a href="write">글쓰기</a></h3>

<!-- 테이블 -->
<table border="1" width="800">
	<thead>
		<tr>
			<th>번호</th>
			<th width="40%">제목</th>
			<th align="left">작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>좋아요</th>
			<th>그룹</th>
			<th>대상</th>
			<th>차수</th>
		</tr>
	</thead>
	<tbody align="center">
		<!-- 공지사항을 출력 -->
		<c:forEach var="boardDto" items="${noticeList}">
		<tr bgcolor="#dfe6e9">
			<td>${boardDto.boardNo}</td>
			<td align="left">
				<!-- 제목을 누르면 상세로 이동 -->
				<a href="detail?boardNo=${boardDto.boardNo}">
					
					<c:if test="${boardDto.boardHead != null}">
						<!-- 말머리가 있으면 출력 -->
						[${boardDto.boardHead}]
					</c:if>
					
					${boardDto.boardTitle}
				</a>
			</td>
			<td align="left">${boardDto.boardWriter}</td>
			
			<%-- DTO에 만든 가상의 Getter 메소드를 불러 처리 --%>
			<td>${boardDto.boardTimeAuto}</td>
			
			<td>${boardDto.boardRead}</td>
			<td>${boardDto.boardLike}</td>
		</tr>
		</c:forEach>
	
		<!-- 검색 또는 목록 결과를 출력 -->
		<c:forEach var="boardDto" items="${list}">
		<tr>
			<td>${boardDto.boardNo}</td>
			<td align="left">
				<!-- boardDepth만큼 띄어쓰기를 실시 -->
				<c:forEach var="i" begin="1" end="${boardDto.boardDepth}">
					&nbsp;&nbsp;
				</c:forEach>
				<!-- boardDepth가 1 이상일 경우만 답글 표식을 추가 -->
				<c:if test="${boardDto.boardDepth > 0}">
					→
				</c:if>
			
				<!-- 제목을 누르면 상세로 이동 -->
				<a href="detail?boardNo=${boardDto.boardNo}">
					
					<c:if test="${boardDto.boardHead != null}">
						<!-- 말머리가 있으면 출력 -->
						[${boardDto.boardHead}]
					</c:if>
					
					${boardDto.boardTitle}
				</a>
			</td>
			<td align="left">${boardDto.boardWriter}</td>
			
			<%-- DTO에 만든 가상의 Getter 메소드를 불러 처리 --%>
			<td>${boardDto.boardTimeAuto}</td>
			
			<td>${boardDto.boardRead}</td>
			<td>${boardDto.boardLike}</td>
			<td>${boardDto.boardGroup}</td>
			<td>${boardDto.boardParent}</td>
			<td>${boardDto.boardDepth}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 페이지 네비게이터 -->
<h2>이전 1 2 3 4 5 6 7 8 9 10 다음</h2>

<!-- 검색창 -->
<form action="list" method="get">

	<c:choose>
		<c:when test="${column == 'board_content'}">
			<select name="column">
				<option value="board_title">제목</option>
				<option value="board_content" selected>내용</option>
				<option value="board_writer">작성자</option>
				<option value="board_head">말머리</option>
			</select>
		</c:when>
		<c:when test="${column == 'board_writer'}">
			<select name="column">
				<option value="board_title">제목</option>
				<option value="board_content">내용</option>
				<option value="board_writer" selected>작성자</option>
				<option value="board_head">말머리</option>
			</select>
		</c:when>
		<c:when test="${column == 'board_head'}">
			<select name="column">
				<option value="board_title">제목</option>
				<option value="board_content">내용</option>
				<option value="board_writer">작성자</option>
				<option value="board_head" selected>말머리</option>
			</select>
		</c:when>
		<c:otherwise>
			<select name="column">
				<option value="board_title" selected>제목</option>
				<option value="board_content">내용</option>
				<option value="board_writer">작성자</option>
				<option value="board_head">말머리</option>
			</select>
		</c:otherwise>
	</c:choose>
	
	
	<input type="search" name="keyword" placeholder="검색어" required value="${keyword}">
	
	<button>검색</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>