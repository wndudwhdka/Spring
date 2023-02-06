<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>포켓몬 정보 변경</title>
</head>
<body>
	<form action="edit" method="post">
		번호 : <input type="text" name="no" value="${pocketmonDto.no}" readonly><br><br>
		이름 : <input type="text" name="name" ><br><br>
		속성 : <input type="text" name="type" ><br><br>
		<button>수정</button>
	</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	