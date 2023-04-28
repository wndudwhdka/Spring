<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>포켓몬스터 목록</h1>
<hr> 
<c:forEach var="dto" items="${list}">
	<img width="150" height="150" src="${dto.imageURL}"><br>
	번호 : ${dto.no}, 이름 : ${dto.name} , 속성 : ${dto.type}
	<hr>
</c:forEach>








