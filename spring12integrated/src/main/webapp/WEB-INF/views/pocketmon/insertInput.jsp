<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<title>포켓몬 등록</title>
</head>
<body>
	<h1>포켓몬 등록</h1>
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insertProcess" method="post">
		<div class="container-400">
        <h1 class="center">포켓몬 등록</h1>
        <div class="row center">
            <h3 class="left">도감번호</h3>
            <input type="text" name="no" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">포켓몬이름</h3>
            <input type="text" name="name" required
                    class="form-input w-100">
        </div>
        <div class="row">
            <h3 class="left">포켓몬타입</h3>
            <input type="text" name="type" required
                    class="form-input w-100">
        </div>
        <hr>
        <div class="row right">
            <!--
                form 안에 있는 버튼은 전송용 버튼으로 기본 취급된다.
                문제를 해결하기 위해서는 다음과 같이 처리한다.

                
                1. 버튼에 type을 설정해서 용도를 지정할 수 있다.
                - type="submit" 이면 폼을 전송시키는 버튼
                - type="button" 이면 그냥 버튼 

                2. 버튼 말고 다른 태그를 사용한다
                - a태그, input[type=button] 등을 사용할 수 있다. 
            -->
            <button type="button" class="form-btn positive w-30">등록</button>
            <button type="submit" class="form-btn neutral w-30">목록</button>
        </div>
        
    </div>
	</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	