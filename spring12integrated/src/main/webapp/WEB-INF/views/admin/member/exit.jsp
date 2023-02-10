<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>회원 삭제 확인</title>
<h1>${memberDto.memberId} 회원이 삭제 테이블에 들어갔습니다.</h1>
<a href="list">다시 목록으로</a>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>