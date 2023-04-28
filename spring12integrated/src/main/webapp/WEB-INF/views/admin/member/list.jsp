<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"></jsp:include>

<div class="container-800">
    <div class="row center">
        <h1>회원 목록</h1>
    </div>
    <div class="row">
        <table class="table table-slit">
            <thead>
                <tr>
                    <th>아이디</th>
                    <th>닉네임</th>
                    <th>전화번호</th>
                    <th>생년월일</th>
                    <th>등급</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody class="center">
            	<c:forEach var="memberDto" items="${list}">
				<tr>
					<td>${memberDto.memberId}</td>
					<td>${memberDto.memberNick}</td>
					<td>${memberDto.memberTel}</td>
					<td>${memberDto.memberBirth}</td>
					<td>${memberDto.memberLevel}</td>
					<td>
						<a class="link" href="detail?memberId=${memberDto.memberId}">상세</a>
						<a class="link" href="edit?memberId=${memberDto.memberId}">변경</a>
						<a class="link" href="exit?memberId=${memberDto.memberId}&page=${page}">탈퇴</a>
					</td>
				</tr>
				</c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="row pagination">
    	<!-- 
			페이지 네비게이터 구현
			for(int i=1; i <= totalPage; i++) {} 
		-->
		
		<c:forEach var="i" begin="1" end="${totalPage}" step="1">
			<c:choose>
				<c:when test="${page == i}"><!-- 현재페이지 -->
					<a class="on">${i}</a>
				</c:when>
				<c:otherwise>
					<a href="list?page=${i}">${i}</a><!-- 현재페이지가 아닌 경우 -->
				</c:otherwise>
			</c:choose>
		</c:forEach>
    </div>
</div>

<jsp:include page="/WEB-INF/views/template/adminFooter.jsp"></jsp:include>







