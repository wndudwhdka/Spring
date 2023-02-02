<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과목 등록 페이지</title>
</head>
<body>
	<h1>과목 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insert" method="post">
		과목 : <input type="text" name="name" required="required"> <br><br>
		기간 : <input type="number" name="period" min="0" max="150" step="30"> <br><br>
		가격 : <input name="price"> <br><br>
		유형 : <select name="type">
			<option>온라인</option>
			<option>오프라인</option>
			<option>혼합</option>
		</select>
		<button>등록</button>
	</form>
</body>
</html>