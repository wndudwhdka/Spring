<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓몬 검색</title>
</head>
<body>
	<h1>포켓몬 검색</h1>
	<a href="/pocketmon/all-in-one">전체 목록보기</a>
	<br><br>
	
	<!-- 
		SELECT 입력창을 사용하면 지정한 목록 중에서 선택이 가능
		- <select> 내부에 <option>으로 선택할 수 있는 항목을 배치
		- 이름(name)은 <select>에 작성한다
		- <option>은 값(value)을 설정하여 전송할 데이터를 지정할 수 있다
	-->
	
	<form action="/pocketmon/all-in-one">
<!-- 		<input name="column"> -->
		<select name="column">
			<option value="no">번호</option>
			<option value="name">이름</option>
			<option value="type">속성</option>
		</select>
		<input name="keyword">
		<button>검색</button>
	</form>
	
	<!-- input에 type 속성을 설정하면 입력창의 형태를 바꿀 수 있다 -->
	<h1>포켓몬 상세정보</h1>
	<form action="/pocketmon/detail">
		<input type="number" name="no">
		<button>보기</button>
	</form>
</body>
</html>