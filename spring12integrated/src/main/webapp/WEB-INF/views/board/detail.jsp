<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h2>제목 : ${boardDto.boardTitle}</h2>
<br><br>
<h3>내용 </h3><br>
${boardDto.boardContent}

<br><a href="redirect">이전페이지로</a>
<a href="delete?no=${boardDto.boardNo}">글 삭제</a>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

