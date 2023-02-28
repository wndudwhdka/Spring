<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
	<h1>회원 정보 변경</h1>
<br>
<br>
<br>
<form action="edit" method="post">
<input type="hidden"name="memberId"value="${memberDto.memberId}">

<table border="1" width="400">
		<tbody>
		<tr>
			<th width="30%">닉네임</th>
			<td>
				<input type="text" name="memberNick" required value="${memberDto.memberNick}">
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="tel" name="memberTel" required value="${memberDto.memberTel}">
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type="date" name="memberBirth" required value="${memberDto.memberBirth}">
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="email" name="memberEmail" value="${memberDto.memberEmail}">
			</td>
		</tr>
		<tr>
			<th rowspan="3">주소</th>
			<td>
				<input type="text" name="memberPost" size="6" maxlength="6" 
					placeholder="우편번호" value="${memberDto.memberPost}">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="memberBasicAddr" size="40" 
					placeholder="기본주소" value="${memberDto.memberBasicAddr}">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="memberDetailAddr" size="40" 
					placeholder="상세주소" value="${memberDto.memberDetailAddr}">
			</td>
		</tr>
		<tr>
			<th>레벨</th>
			<td>
<%-- 				<input type="text" name="memberLevel" value="${memberDto.memberLevel}"> --%>
				<c:choose>
					<c:when test="${memberDto.memberLevel == '일반회원'}">
						<select name="memberLevel">
							<option>준회원</option>
							<option selected>일반회원</option>
							<option>우수회원</option>
						</select>
					</c:when>
					<c:when test="${memberDto.memberLevel == '우수회원'}">
						<select name="memberLevel">
							<option>준회원</option>
							<option>일반회원</option>
							<option selected>우수회원</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="memberLevel">
							<option selected>준회원</option>
							<option>일반회원</option>
							<option>우수회원</option>
						</select>
					</c:otherwise>
				</c:choose>
				
				(기존 : ${memberDto.memberLevel})
			</td>
		</tr>
		<tr>
			<th>포인트</th>
			<td>
				<input type="text" name="memberPoint" size="10" value="${memberDto.memberPoint}" required>
				(기존 : ${memberDto.memberPoint} point)
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<button>수정</button>
			</td>
		</tr>
	</tbody>
</table>
</form>
<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>