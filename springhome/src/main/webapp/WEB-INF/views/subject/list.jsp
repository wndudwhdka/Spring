<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

	<div class="container-500 test"> 
	
        <div class="row center">
            <!-- 제목 -->
			<h1>과목 ${mode}</h1>
        </div>
        
        <div class="row center">
            <!-- 검색창 -->
			<form action="list" method="get">
                <select name="column" class="form-input">
                    <option value="name">과정명</option>
                    <option value="period">강의시간</option>
                    <option value="type">강의유형</option>
                </select>
                <input type="search" name="keyword" required
                        class="form-input" placeholder="검색어">
                <button type="submit" class="form-btn neutral">검색</button>
            </form>
        </div>
        
        <div class="row right">
        	<a href="insert" class="form-btn positive">등록하기</a>
        </div>
        
        <div class="row center">
        	<c:choose>
				<c:when test="${list.isEmpty()}">
					<h2>데이터가 존재하지 않습니다</h2>
					<a href="insert" class="form-btn positive">등록하기</a>
				</c:when>
				<c:otherwise>
					<!-- 테이블 -->
					<table class="table table-border">
						<thead>
							<tr>
								<th>번호</th>
								<th width="40%">과정명</th>
								<th>강의시간</th>
								<th>수강료</th> 
								<th>수업방식</th>
							</tr>
						</thead>
						<tbody class="center">
							<c:forEach var="subjectDto" items="${list}">
							<tr>
								<td>${subjectDto.no}</td>
								<td class="left">
									<a href="detail?no=${subjectDto.no}">
										${subjectDto.name}
									</a>
								</td>
								<td>${subjectDto.period}</td>
								<td align="right">${subjectDto.price}</td>
								<td>${subjectDto.type}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
        </div>
        
        <div class="row right">
        	<a href="insert" class="form-btn positive">등록하기</a>
        </div>
        
    </div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
	





