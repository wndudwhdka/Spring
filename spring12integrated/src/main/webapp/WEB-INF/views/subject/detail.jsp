<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>과목 상세 정보</title>
</head>
<body>
	<h1>과목상세정보</h1>
	번호 : ${dto.no}<br>
	과목 : ${dto.name }<br>
	기간 : ${dto.period }<br>
	가격 : ${dto.price }<br>
	타입 : ${dto.type }<br>
	<a href="delete?no=${dto.no}">삭제</a>
</body>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	