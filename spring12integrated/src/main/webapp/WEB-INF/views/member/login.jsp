<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 <h1>로그인</h1>
 
 <form action="login" method="post">
	아이디 : <input type="text" name="memberId" required> <br><br>
	비밀번호 : <input type="password" name="memberPw" required> <br><br>
	<button>로그인</button>
</form>

<h2><a href="/member/find">아이디가 기억나지 않습니다.</a></h2>

<%-- 
	본 페이지 접근법 두 가지 
	1. 일반적인 접근 : 메뉴 또는 회원가입완료 페이지의 링크로 이동
	2. 로그인 실패 시 리다이렉트로 이동
	- 구분을 하기 위해서 2번의 경우 파라미터에 mode-error를 추가한다. 
	- 파라미터를 검사해서 로그인 실패인 경우 추가 메세지를 출력한다.
	- JSP에서 파라미터를 직접 읽으려면 ${param.이름}과 같이 작성
	- EL은 equals 대신 ==로 문자열 비교가 가능
	- EL은 문자열을 쌍따옴표 또는 외따옴표 구분하지 않고 사용 
 --%>
 

 
<c:if test="${param.mode== 'error'}">
	<h1>에러가 발생했습니다.</h1>
</c:if>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>