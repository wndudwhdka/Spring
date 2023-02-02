<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 학생 등록 페이지  </title>
</head>
<body>
	<h1>학생 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insert" method="post">
		이름 : <input type="text" name="name" required="required"> <br><br>
		국어 : <input type="text" name="korean"> <br><br>
		영어 : <input name="english"> <br><br>
		수학 : <input name="math"> <br><br>
		<button>등록</button>
	</form>
</body>
</html>