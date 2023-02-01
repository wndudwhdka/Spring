<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 페이지입니다.</title>
</head>
<body>
<h1>학생 정보 페이지</h1><br><br><br>
	
	<h2>학생 정보 등록</h2>
	<form action="/student/insert">
		<input name="name">학생이름<br><br>
		<input type="number" name="korean">국어점수<br><br>
		<input type="number" name="english">영어점수<br><br>
		<input type="number" name="math">수학점수<br><br>
		<button>등록</button>
	</form>
	
	<h2>학생 전체 목록</h2>
	<a href="/student/list">학생목록보기</a>
	<br><br>
	
	<h2>학생 검색</h2>
	<form action="/student/search">
		<select name="column">
			<option value="no">번호</option>
			<option value="name">이름</option>
			<option value="korean">국어</option>
			<option value="english">영어</option>
			<option value="math">수학</option>
		</select>
		<input name="keyword"><br><br>
		<button>검색</button>
	</form>
	<br><br>

	<h2>학생 상세검색</h2>
	<form action="/student/detail">
		<input type="number" name="no">학생번호를 입력하세요
		<button>검색</button>
	</form>	
	
	<h2>학생 정보 수정</h2>
	<form action="/student/edit">
		<input type="number" name="no">수정할 학생 번호<br><br>
		<input name="name">학생이름<br><br>
		<input type="number" name="korean">국어점수<br><br>
		<input type="number" name="english">영어점수<br><br>
		<input type="number" name="math">수학점수<br><br>
		<button>수정</button>
	</form>
	
	<h2>학생 정보 삭제</h2>
	<form action="/student/delete">
		<input type="number" name="no">삭제할 학생 번호<br><br>
		<button>삭제</button>
	</form>
	
</body>
</html>