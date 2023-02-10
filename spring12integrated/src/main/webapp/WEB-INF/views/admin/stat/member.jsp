<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>회원현황</h1>
<a href="member">등급순</a>
<a href="member?sort=cnt desc">인원많은순</a>
<a href="member?sort=cnt asc">인원적은순</a>

<!-- <a href="membe" -->
<table border="1"width="1000">
	<thead>
		<tr aligh="center">
			<th>맴버레벨</th>
			<th>레벨당맴버수</th>
			<th>레벨당맴버포인트합</th>
			<th>레벨당맴버포인트평균</th>
			<th>레벨당맴버포인트최대</th>
			<th>레벨당맴버포인트최소</th>
		</tr>
	
	</thead>
	<tbody>
		<c:forEach var="MemberStatDto" items="${list}">
		<tr align="right">
			<td align="center">${MemberStatDto.member_level}</td>
			<td >${MemberStatDto.cnt}</td>
			<td>${MemberStatDto.pointSum}</td>
			<td>${MemberStatDto.pointAvg}</td>
			<td>${MemberStatDto.pointMax}</td>
			<td>${MemberStatDto.pointMin}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>