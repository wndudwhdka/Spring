<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-600">
	<div class="row center">
		<!-- 제목 -->
		<c:choose>
			<c:when test="${column == null}">
				<h2>학생 목록</h2>
			</c:when>
			<c:otherwise>
				<h2>학생 검색</h2>
			</c:otherwise>
		</c:choose>
	</div> 
	
	<div class="row right">
		<a href="insert" class="form-btn neutral">신규 학생 등록</a>
	</div>

	<div class="row center">
		<!-- 검색창 -->
		<form action="list" method="get">
			<input class="form-input" type="search" name="keyword" placeholder="검색할 학생명" value="${keyword}">
			<button class="form-btn positive">검색</button>
		</form>
	</div>
	
	<div class="row center">
	<!-- 테이블 -->
	<c:choose>
		<c:when test="${list.isEmpty()}">
			<h2>데이터가 존재하지 않습니다</h2>
		</c:when>
		<c:otherwise>
			<table class="table table-border">
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
<!-- 						<th>국어</th> -->
<!-- 						<th>영어</th> -->
<!-- 						<th>수학</th> -->
						<th>총점</th>
<!-- 						<th>평균</th> -->
						<th>관리</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach var="studentDto" items="${list}">
					<tr>
						<td>${studentDto.no}</td>
						<td>
							<a class="link" href="detail?no=${studentDto.no}">
								${studentDto.name}
							</a>
						</td>
<%-- 						<td>${studentDto.korean}</td> --%>
<%-- 						<td>${studentDto.english}</td> --%>
<%-- 						<td>${studentDto.math}</td> --%>
						<td>${studentDto.total}</td>
<%-- 						<td>${studentDto.average}</td> --%>
						<td>
							<a class="link" href="#">수정</a>
							<a class="link" href="delete?no=${studentDto.no}&keyword=${keyword}">삭제</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
	</div>
	
	<div class="row right">
		<a href="insert" class="form-btn neutral">신규 학생 등록</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






