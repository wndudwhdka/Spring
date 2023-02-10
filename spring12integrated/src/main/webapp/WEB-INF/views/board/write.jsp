<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<h1>게시글 작성</h1>

<h3>제목</h3>
<form action="write" method="get">
<textarea rows="1" cols="50" required></textarea>

<h3>내용</h3>
<textarea rows="30" cols="50" required ></textarea>
<button >작성 완료</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

