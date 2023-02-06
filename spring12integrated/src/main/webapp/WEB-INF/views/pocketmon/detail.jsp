<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>몬스터 상세정보</title>
</head>
<body>
	<h1>몬스터 상세정보 입니다.</h1>
	<h1>${pocketmonDto.getNo()}번 몬스터는 ${pocketmonDto.getName()}이고 ${pocketmonDto.getType()}입니다. </h1>
	
	<h2><a href="list">목록으로 이동</a></h2>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	