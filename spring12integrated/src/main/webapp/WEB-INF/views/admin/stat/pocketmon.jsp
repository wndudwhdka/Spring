<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/static/js/admin-pocketmon.js"></script>

<div class="container-300">
	<div class="row center">
		<h2>포켓몬 현황</h2>
	</div>
	<div class="row">
		<canvas id="myChart"></canvas>
	</div>
	<div class="row">
		<table class="table table-border">
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
</div>


<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>