<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-400">
	<div class="row center">
		<h2>${subjectDto.name} 과목 정보</h2>
	</div>
	<div class="row">
		<table class="table table-border">
			<tr>
				<th class="w-30">과목번호</th>
				<td>${subjectDto.no}</td>
			</tr>
			<tr>
				<th>과정명</th>
				<td>${subjectDto.name}</td>
			</tr>
			<tr>
				<th>강의시수</th>
				<td>${subjectDto.period} 시간</td>
			</tr>
			<tr>
				<th>월 수강료</th>
				<td>${subjectDto.price} 원</td>
			</tr>
			<tr>
				<th>강의유형</th>
				<td>${subjectDto.type}</td>
			</tr>
		</table>
	</div>
	
	<div class="row right">
		<a class="form-btn neutral" href="list">목록으로</a>
		<a class="form-btn negative" href="delete?no=${subjectDto.no}">지우기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>