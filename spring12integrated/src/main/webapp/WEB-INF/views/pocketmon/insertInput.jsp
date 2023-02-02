<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓몬 등록</title>
</head>
<body>
	<h1>포켓몬 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insertProcess" method="post">
		번호 : <input name="no"> <br><br>
		이름 : <input name="name"> <br><br>
		속성 : <input name="type"> <br><br>
		<button>등록</button>
	</form>
</body>
</html>