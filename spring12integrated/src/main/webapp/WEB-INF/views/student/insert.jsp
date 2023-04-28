<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form autocomplete="off" action="insert" method="post">

<div class="container-400">
	<div class="row center">
		<h2>학생 정보 등록</h2>
	</div>
	<div class="row">
		<label>이름<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="name" required class="form-input w-100">
	</div>
	<div class="row">
		<label>국어<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="korean"  required class="form-input w-100">
	</div>
	<div class="row">
		<label>영어<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="english"  required class="form-input w-100">
	</div>
	<div class="row">
		<label>수학<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="math"  required class="form-input w-100">
	</div>
	<div class="row right">
		<button type="submit" class="form-input positive">등록</button>
		<a href="list" class="form-btn neutral">목록</a>
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>





