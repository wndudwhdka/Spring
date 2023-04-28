<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-400">
	<div class="row center">
		<h2>포켓몬 정보</h2>
	</div>
	<div class="row center">
		<img class="image image-circle" src="${pocketmonDto.imageURL}" width="150" height="150">
	</div>
	<div class="row">
		<table class="table table-border">
			<tr>
				<th class="w-30">번호</th>
				<td>${pocketmonDto.no}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${pocketmonDto.name}</td>
			</tr>
			<tr>
				<th>속성</th>
				<td>${pocketmonDto.type}</td>
			</tr>
		</table>
	</div>
	<div class="row center">
		<h2><a class="link" href="list">목록으로 이동</a></h2>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


