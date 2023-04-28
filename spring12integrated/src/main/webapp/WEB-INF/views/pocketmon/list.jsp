<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-400">
	<div class="row center">
		<h2>포켓몬 목록/검색</h2>
	</div>
	<div class="row center">
		<h3><a class="link" href="insertInput">신규 등록</a></h3>
	</div>
	
	<div class="row center">
		<!-- 검색을 위한 검색창도 존재해야 한다 -->
		<form action="list" method="get">
			<select name="column" class="form-input">
				<option value="name">이름</option>
				<option value="type">속성</option>
			</select>
			<input class="form-input" type="search" name="keyword" placeholder="검색어" required>
			<button class="form-btn positive">검색</button>
		</form>
	</div>
	
	<div class="row">
		<table class="table table-border">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>속성</th>
					<th>이미지</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody class="center">
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
						<img style="vertical-align:bottom;" src="${pocketmonDto.imageURL}" width="25" height="25">
					</td>
					<td>
						<a class="link" href="edit?no=${pocketmonDto.no}">수정</a>
						<a class="link confirm-link" data-message="정말 삭제하시겠습니까?" href="delete?no=${pocketmonDto.no}">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>








