<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div class="container-800">
       <div class="row center">
           <h1>회원목록</h1>
       </div>
       <div class="row">
           <table class="table table-border table-hover">
                <thead>
                   <tr>
                       <th>안녕</th>
                       <th>닉네임</th>
                       <th>전화번호</th>
                       <th>생년월일</th>
                       <th>등급</th>
                       <th>변경</th>
                   </tr>
              	</thead>
               	<tbody>
					<c:forEach var="memberDto" items="${list}">
					<tr>
						<td>${memberDto.memberId}<br></td>
						<td>${memberDto.memberNick}<br></td>
						<td>${memberDto.memberTel}</td>
						<td>${memberDto.memberBirth}</td>
						<td>${memberDto.memberLevel}</td>			
						<td>
						<a class="link" href="detail?memberId=${memberDto.memberId}">상세</a>	
						<a class="link" href="password?memberId=${memberDto.memberId}">변경</a>
						<a class="link" href="exit?memberId=${memberDto.memberId}&page=${page}">탈퇴	</a>
						</td>
					</tr>
					</c:forEach>
			  	</tbody>
           </table>
      </div>



<!-- 	<!-- 페이지 네이게이터 구현 -->
<%-- 	<c:forEach var="i" begin="1" end="${totalPage}" step="1"> --%>
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${page==i}"><!-- 현재 페이지 --> --%>
<%-- 				${i} --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 				<a href="list?page=${i}">${i}</a> <!-- 현재 페이지가 아닌경우 --> --%>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
<%-- 	</c:forEach> --%>


	<div class="row pagination center">
 
		<!-- 처음 -->
    	<c:choose>
			<c:when test="${vo.first}">
				<a class="disabled">&laquo;</a>
			</c:when>
			<c:otherwise>
				<a href="list?page=1">&laquo;</a>
			</c:otherwise>
		</c:choose>
		
		<!-- 이전 -->
		<c:choose>
			<c:when test="${vo.prev}">
				<a href="list?page=${vo.prevPage}">&lt;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&lt;</a>
			</c:otherwise>
		</c:choose>
		
		<!-- 숫자 -->
        <c:forEach var="i" begin="${vo.startBlock}" end="${vo.finishBlock}">
			<c:choose>
				<c:when test="${vo.page == i}">
					<a class="on">${i}</a>
				</c:when>
				<c:otherwise>
					<a href="list?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
	
		<!-- 다음 -->
		<c:choose>
			<c:when test="${vo.next}">
				<a href="list?page=${vo.nextPage}">&gt;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&gt;</a>
			</c:otherwise>
		</c:choose>
		
		<!-- 마지막 -->
		<c:choose>
			<c:when test="${vo.last}">
				<a class="disabled">&raquo;</a>
			</c:when>
			<c:otherwise>
				<a href="list?page=${vo.totalPage}">&raquo;</a>
			</c:otherwise>
		</c:choose>
    </div>
</div>
	
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

