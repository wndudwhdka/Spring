<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>나의 정보입니다. </h1><br><br>
<table border="1" width="400">
	<tbody>
		<tr>
			<th width="25%">아이디</th>
			<td>${memberDto.memberId}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${memberDto.memberNick}</td>
		</tr>
		<tr>
			<th>레벨</th>
			<td>${memberDto.memberLevel}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${memberDto.getMemberTel()}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${memberDto.getMemberEmail() }</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${memberDto.getMemberBirth() }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${memberDto.getMemberPost()}</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${memberDto.memberJoin}</td>
		</tr>
		<tr>
			<th>최종 로그인 일시</th>
			<td>${memberDto.memberLogin}</td>
		</tr>
	</tbody>
</table>

<!-- 회원 관리 메뉴 -->
<h2><a href="password">비밀번호 변경</a></h2>
<h2><a href="edit">개인정보 변경</a></h2>
<h2><a href="exit">회원 탈퇴</a></h2>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
