<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>과목 등록 페이지</title>
</head>
<body>
	<h1>과목 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insert" method="post">
<div class="container-400">

        <h1 class="center">과목 정보 등록</h1>
        <div class="row">
            <h3 class="left">과목명</h3>
            <input type="text" name="name" required
                    class="form-input w-100">
        </div>

        <div class="row center">
            <h3 class="left">과목번호</h3>
            <input type="number" name="no" required
                    class="form-input w-100">
        </div>

        <div class="row">
            <h3 class="left">과목시간</h3>
            <input type="number" name="period" required
                    class="form-input w-100">
        </div>

        <div class="row">
            <label class="left">수강료</label>
            <input type="number" name="price" required
                    class="form-input w-100">
        </div>

        <div class="row">
            <label class="w-100">강의 유형</label>
            <select name="type" required class="form-input">
                <option>온라인</option>
                <option>오프라인</option>
                <option>혼합형</option>
            </select>

        </div>

        <hr>
        <div class="row center">
            <a class="form-btn neutral w-20">목록</a>
            <button class="form-btn positive w-20">등록</button>
        </div>
    </div>
	</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>