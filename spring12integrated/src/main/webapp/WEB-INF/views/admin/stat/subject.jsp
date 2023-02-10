<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table border="1"width="400">
	<thead>
		<tr>
			<th>수업형태</th>
			<th>형태별강좌가격평균</th>
			<th>형태별강좌수</th>
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

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>