<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1"width="400">
	<thead>
		<tr>
			<th>속성</th>
			<th>마리수</th>
		</tr>
	
	</thead>
	<tbody>
		<c:forEach var="pocketmonStatDto" items="${list}">
		<tr>
			<td>
			<a href="/pocketmon/list?column=type&keyword=${pocketmonStatDto.type}">
			${pocketmonStatDto.type}
			</a>
			</td>
			<td>${pocketmonStatDto.cnt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>