<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-500">
<table class="table table-slit">
	<thead>
		<tr>
			<th><h3>속성</h3></th>
			<th><h3>마리수</h3></th>
		</tr>
	
	</thead>
	<tbody>
		<c:forEach var="pocketmonStatDto" items="${list}">
		<tr class="center">
			<td>
			<a class="link" href="/pocketmon/list?column=type&keyword=${pocketmonStatDto.type}">
			${pocketmonStatDto.type}
			</a>
			</td>
			<td>${pocketmonStatDto.cnt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>