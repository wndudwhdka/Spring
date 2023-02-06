<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>학생 목록 페이지</title>
</head>
<body>
<c:choose>
	<c:when test="${list.isEmpty()}">
		<h2>데이터가 존재하지 않습니다.</h2>
		<a href="insert">등록하기</a>
	</c:when>
	<c:otherwise>
	<h1>학생 목록</h1>
	<br><br>
	<table border="1" width="1000">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>총점</th>
				<th>평균</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="studentDto" items="${list}">	
				<tr>
					<td>${studentDto.no}</td>
					<td >
						<a href="detail?no=${studentDto.no}">
						${studentDto.name}
						</a>
					</td>
					<td>${studentDto.korean}</td>
					<td>${studentDto.english}</td>
					<td>${studentDto.math}</td>
					<td>${studentDto.total}</td>
					<td>${studentDto.average}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="insert">신규등록</a>
	
	<form action="list"method="get">
		<select name="column">
			<option value="no">번호</option>
			<option value="name">이름</option>
			<option value="korean">국어</option>
			<option value="english">영어</option>
			<option value="math">수학</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어" required>
		<button>검색</button>
	</form>
	<c:forEach var="studentDto" items="${list}">
		<h2>
		${studentDto}
		<a href="detail?no=${studentDto.no}">보기</a>
		</h2>
	</c:forEach>
	</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	