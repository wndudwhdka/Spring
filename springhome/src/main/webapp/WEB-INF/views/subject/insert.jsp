<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="insert" method="post">
<div class="container-400">
	<div class="row center">
		<h2>과목 정보 등록</h2>
	</div>
	<div class="row">
		<label>과정명<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="name" required>
	</div>
	<div class="row">
		<label>시수<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="period" required>
	</div>
	<div class="row">
		<label>판매가<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="price" required>
	</div>
	<div class="row">
		<label class="form-label w-100">
			유형
			<i class="fa-solid fa-asterisk"></i>
		</label>
		<select name="type" class="form-input" required>
			<option value="">선택하세요</option>
			<option>오프라인</option>
			<option>온라인</option>
			<option>혼합
		</select>
	</div>
	<div class="row">
		<button class="form-btn positive w-100" type="submit">등록</button>	
	</div>
</div>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



