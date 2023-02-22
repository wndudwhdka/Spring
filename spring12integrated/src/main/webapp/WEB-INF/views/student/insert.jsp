<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title> 학생 등록 페이지  </title>
</head>
<body>
	<h1>학생 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insert" method="post">
		<div class="container-400">
        <h1 class="center">성적 입력</h1>
        <div class="row center">
            <h3 class="left">번호</h3>
            <input type="text" name="no" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">이름</h3>
            <input type="text" name="name" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">국어</h3>
            <input type="text" name="korean" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">영어</h3>
            <input type="text" name="english" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">수학</h3>
            <input type="text" name="math" required
                    class="form-input w-100">
        </div>
        <hr>
        <div class="row center">
        <button class="form-btn neutral w-30"><a href="list" class="form-btn neutral w-20">목록</a></button>
        <button class="form-btn positive w-30">등록</button>
        </div>
    </div>
	</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	