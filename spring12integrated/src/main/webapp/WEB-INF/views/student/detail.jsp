<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container-400">
	<div class="row center">
		<h2>학생 상세 정보</h2>
	</div>
	<div class="row flex-box flex-vertical">
		<div class="flex-box">
			<div class="w-25 center">번호</div>
			<div class="w-75">${studentDto.no}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">이름</div>
			<div class="w-75">${studentDto.name}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">국어</div>
			<div class="w-75">${studentDto.korean}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">영어</div>
			<div class="w-75">${studentDto.english}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">수학</div>
			<div class="w-75">${studentDto.math}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">총점</div>
			<div class="w-75">${studentDto.total}</div>
		</div>
		<div class="flex-box">
			<div class="w-25 center">평균</div>
			<div class="w-75">${studentDto.average}</div>
		</div>
	</div>
	
	<div class="row right">
		<a class="form-btn neutral" href="list">목록보기</a>
		<a class="form-btn negative" href="delete?no=${studentDto.no}">지우기</a>
	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



