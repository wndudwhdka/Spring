<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>학생 상세 정보</title>
</head>
<body>
	<h1>학생상세정보</h1>
ul입니다.
<ul>
	<li>번호 : ${dto.no}</li>
	<li>이름 : ${dto.name}</li>
	<li>국어 : ${dto.korean}</li>
	<li>영어 : ${dto.english}</li>
	<li>수학 : ${dto.math}</li>
</ul>
ol입니다.
<ol>
	<li>번호 : ${dto.no}</li>
	<li>이름 : ${dto.name}</li>
	<li>국어 : ${dto.korean}</li>
	<li>영어 : ${dto.english}</li>
	<li>수학 : ${dto.math}</li>
</ol>
	<a href="insert">등록 페이지로</a>
	<a href="list">목록 페이지로</a>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include> 