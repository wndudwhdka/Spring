<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과목 정보 페이지 입니다.</title>
</head>
<body>
	<h1>과목 정보 페이지</h1><br><br><br>
	
	<h2>과목 정보 등록</h2>
	<form action="../subject/insert">
		과목:<input type="text" name="name" required="required"><br><br>
		기간:<input type="number" name="period" min="0" max="150" step="30" required="required" placeholder="기간"><br><br>
		가격:<input type="number" name="price" required="required"><br><br>
		유형:
		<select name="type" required>
			<option >온라인</option>
			<option >오프라인</option>
			<option >혼합</option>
		</select>
		<button>등록</button>
	</form>
	
	<h2>과목 전체 목록</h2>
	<a href="/subject/list">전체목록보기</a>
	<br><br>
	
	<h2>과목 검색</h2>
	<form action="/subject/search">
		<select name="column">
			<option value="no">번호</option>
			<option value="name">과목명</option>
			<option value="period">기간(30개월단위)</option>
			<option value="price">가격</option>
			<option value="type">타입</option>
		</select>
		<input name="keyword"><br><br>
		<button>검색</button>
	</form>
	<br><br>

	<h2>과목 상세검색</h2>
	<form action="/subject/detail">
		<input type="number" name="no">과목번호를 입력하세요
		<button>검색</button>
	</form>	
	
	<h2>과목 수정</h2>
	<form action="/subject/edit">
		<input type="number" name="no">수정할 과목 번호<br><br>
		<input name="name">과목명<br><br>
		<input name="period">기간(30개월단위)<br><br>
		<input name="price">가격<br><br>
		<input name="type"><br><br>
		<button>수정</button>
	</form>
	
	<h2>과목 삭제</h2>
	<form action="/subject/delete">
		<input type="number" name="no">삭제할 과목 번호<br><br>
		<button>삭제</button>
	</form>
	
</body>
</html>