<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-500">
<table class="table table-slit">
	<thead>
		<tr>
			<th>��������</th>
			<th>���º����°������</th>
			<th>���º����¼�</th>
		</tr>
	
	</thead>
	<tbody align="center">
		<c:forEach var="subjectStatDto" items="${list}">
		<tr>
			<td>${subjectStatDto.type}</td>
			<td align="right">
				<fmt:formatNumber value="${subjectStatDto.aver}" pattern="#,##0.00"></fmt:formatNumber>
			</td>
			<td>${subjectStatDto.cnt}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>