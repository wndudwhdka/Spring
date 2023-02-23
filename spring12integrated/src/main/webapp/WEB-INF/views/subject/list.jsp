<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-600">
	<c:choose>
			<c:when test="${list.isEmpty()}">
				<div class="row center">
					<h2>데이터가 존재하지 않습니다.</h2>
					<a href="insert">등록하기</a>
				</div>
			</c:when>	
			<c:otherwise>
				<div class="row center">
				<h1>과목 목록</h1>
				</div>
				<div class="row center">
				<form action="list" method="get">
					<select name="column">
						<option value="no">번호</option>
						<option value="name">과목</option>
						<option value="period">기간</option>
						<option value="price">가격</option>
						<option value="type">방식</option>
					</select> <input type="text" name="keyword" placeholder="검색어"
						value="${keyword}">
					<button class="form-btn form-btn positive ">검색</button>
				</form>
				</div>
				<c:choose>
					<c:when test="${list.isEmpty()}">
						<h2>데이터가 존재하지 않습니다.</h2>
					</c:when>
				</c:choose>	
				<div class="row center">
				<table class="table table-slit">
					<thead>
						<tr>
							<th>번호</th>
							<th width="40%">과목</th>
							<th>기간</th>
							<th>가격</th>
							<th>방식</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:forEach var="subjectDto" items="${list}">
							<tr>
								<td>${subjectDto.no}</td>
								<td align="left"><a class="link" href="detail?no=${subjectDto.no}">
										${subjectDto.name} </a></td>
								<td>${subjectDto.period}</td>
								<td>${subjectDto.price}</td>
								<td>${subjectDto.type}</td>
								<td>
								<a class="link" href="">수정</a>
								<a class="link" href="delete?no=${subjectDto.no}">삭제</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<a class="link" href="insert">신규등록</a>
				<br>
				<br>
				<c:forEach var="subjectDto" items="${list}">
					<h2>
						${subjectDto} <a class="link" href="detail?np=${subjectDto.no}">보기</a>
					</h2>
				</c:forEach>
			</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	