<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>개인정보 변경</h1>

<!-- 테이블부터 th/td 사이에는 어떠한 태그도 적을 수 없다.  -->

<form action="editFinish" method="post">
<table border="0" width="400">
	<tbody>
		<tr>
			<th width="25%">이름</th>
			<td>입력창</td>		
		</tr>
		<tr>
			<th>비밀번호확인</th>
			<td>
				<input type="password" name="memberPw"required>
			</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>
				<input type="text"name="memberNick"required value="${memberDto.memberNick}">
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="tel"name="memberTel"required value="${memberDto.memberTel}">
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type="date"name="memberBirth"required value="${memberDto.memberBirth}">
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="text"name="memberEmail"required value="${memberDto.memberEmail}">
			</td>
		</tr>
		<tr>
			<th rowspan="3">우편번호</th>
			<td>
				<input type="text"name="memberPost"required value="${memberDto.memberPost}">
			</td>
		</tr>
		<tr>
<!-- 			<th >기본주소</th> -->
			<td>
				<input type="text"name="memberBasicAddr"required value="${memberDto.memberBasicAddr}" placeholder="기본주소">
			</td>
		</tr>
		<tr>
<!-- 			<th >상세주소</th> -->
			<td>
				<input type="text"name="memberDetailAddr"required value="${memberDto.memberDetailAddr}" placeholder="상세	주소">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<button>수정하기</button>
			</td>
		</tr>
	</tbody>
</table>
</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>