<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
	input[name=memberId] {
		background-image: url("/static/image/id.png");
	}
	input[name=memberPw] {
		background-image: url("/static/image/password.png");
	}
</style>

<form action="/member/login" method="post" autocomplete="off">

<div class="container-400">
	<div class="row center">
		<h2>로그인</h2>
	</div>
	<div class="row">
		<label>아이디<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="memberId" required class="form-input w-100 form-input-bg">
	</div>
	<div class="row">
		<label>비밀번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" name="memberPw" required class="form-input w-100 form-input-bg">
	</div>
	<div class="row">
		<button type="submit" class="form-btn positive w-100">로그인</button>
	</div>	
	<div class="row center">
		<a href="find" class="link">아이디가 기억나지 않습니다</a>
	</div>
	
	<%--
		이 페이지에 접근 가능한 방법은 2가지가 있다.
		1. 일반적인 접근 : 메뉴 또는 회원가입완료 페이지의 링크로 이동
		2. 로그인 실패 시 리다이렉트로 이동
		- 구분을 하기 위해서 2번의 경우 파라미터에 mode=error를 추가했다.
		- 파라미터를 검사해서 로그인 실패인 경우 추가 메세지를 출력
		- JSP에서 파라미터를 직접 읽으려면 ${param.이름}과 같이 작성
		- ${param.mode}는 파라미터 중 mode라는 항목의 값을 불러오라는 의미
		- EL은 equals 대신 == 로 문자열 비교가 가능
		- EL은 문자열을 쌍따옴표 또는 외따옴표 구분하지 않고 사용
	--%>
	<c:if test="${param.mode == 'error'}">
		<div class="row center" style="color:red;">
			<h3>로그인 정보가 일치하지 않습니다</h3>
		</div>
	</c:if>
</div>
</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>







