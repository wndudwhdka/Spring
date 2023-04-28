<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 
	수정 입력 페이지에서는 전달받은 정보를 value로 표시해야 된다
	- 사실상 상세페이지를 입력창으로 표시한 형태와 같다 
	- PK(Primary Key)는 변경이 불가하도록 처리
		1. 입력창 잠금(readonly 속성 추가)
		2. 입력창의 type을 hidden으로 설정하면 표시가 되지 않는다
-->
<form action="edit" method="post">
<input type="hidden" name="no" value="${pocketmonDto.no}"> <br><br>

<div class="container-400">
	<div class="row center">
		<h2>포켓몬 정보 변경</h2>
	</div>
	<div class="row">
		<label>이름</label>
		<input type="text" name="name" value="${pocketmonDto.name}" class="form-input w-100">
	</div>
	<div class="row">
		<label>속성</label>
		<input type="text" name="type" value="${pocketmonDto.type}" class="form-input w-100">
	</div>
	<div class="row right">
		<button type="submit" class="form-btn positive">수정</button>
		<a href="list" class="form-btn neutral">목록</a>
	</div>
</div>

</form>	

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

