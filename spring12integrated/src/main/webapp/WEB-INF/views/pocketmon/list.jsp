<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>포켓몬 목록</title>

<div class="container-700">
	<div class="row center">
	<h1>포켓몬 목록</h1>
	</div>
		<div class="row right">
		<a class="form-btn form-btn positive" href="insertInput">신규등록</a>
	</div>
	<div class="row center">
	<div class="row"></div>
	<table class="table table-slit">
		<thead>
		 <tr>
		 	<th>도감번호</th>
		 	<th>이름</th>
		 	<th>속성</th>
		 	<th>관리</th>
		 </tr>
		</thead>
		<tbody>
			<c:forEach var="pocketmonDto" items="${list}">
			<tr>
				<td>${pocketmonDto.no}</td>
				<td>
					<a class="link" href="detail?no=${pocketmonDto.no}">		
					${pocketmonDto.name}
					</a>	
				</td>
				<td>${pocketmonDto.type}</td>
				<td>
					<a class="form-btn form-btn neutral" href="edit?no=${pocketmonDto.no}">수정</a>
					<a class="form-btn form-btn neutral" href="delete?no=${pocketmonDto.no}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>

	<div class="row center">
		<form action="list"method="get">
			<select name="column">
				<option value="name">이름</option>
				<option value="type">속성</option>		
			</select>
			<input type="text"name="keyword"placeholder="검색어"required>
			<button class="form-btn form-btn neutral">검색</button>
		</form>
	</div>

	
	
<%-- 	<c:forEach var="pocketmonDto" items="${list}"> --%>
<!-- 		<h2> -->
<%-- 		${pocketmonDto} --%>
<%-- 		<a href="detail?no=${pocketmonDto.no}">보기</a> --%>
<!-- 		</h2> -->
<%-- 	</c:forEach> --%>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	
