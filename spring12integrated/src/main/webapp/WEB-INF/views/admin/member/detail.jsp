<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>회원 상세 정보</title>

<br>
	<h1>최종 로그인 : ${memberDto.memberLogin}</h1>
<br>
<br>
<br>
<table border="2" width="2000">
	<thead>
		<tr>
			<th>이름</th>
			<th>닉네임</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>생년월일</th>
			<th>우편번호</th>
			<th>기본주소</th>
			<th>상세주소</th>
			<th>등급</th>
			<th>포인트</th>
			<th>가입날짜</th>
			<th>접속날짜</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${memberDto.getMemberId()}</td>
			<td>${memberDto.memberLogin}</td>
<%-- 			<td>${memberDto.getMemberNick()}</td> --%>
			<td>${memberDto.getMemberTel()}</td>
			<td>${memberDto.getMemberEmail()}</td>
			<td>${memberDto.getMemberBirth()}</td>
			<td>${memberDto.getMemberPost()}</td>
			<td>${memberDto.getMemberBasicAddr()}</td>
			<td>${memberDto.getMemberDetailAddr()}</td>
			<td>${memberDto.getMemberLevel()}</td>
			<td>${memberDto.getMemberPoint()}</td>
			<td>${memberDto.getMemberJoin()}</td>
			<td>${memberDto.getMemberLogin()}</td>
		</tr>
	</tbody>
</table>	
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>