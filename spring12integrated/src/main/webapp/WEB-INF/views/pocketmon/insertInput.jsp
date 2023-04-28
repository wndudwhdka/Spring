<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

	<!-- 
		form 전송방식은 크게 두 가지로 나뉜다
		- GET : 기본 방식. 주소를 통해 데이터를 전달하는 방식
			- 마치 편지봉투에 할 말을 작성하는 것과 유사하다
			- 간편하게 사용할 수 있다는 장점이 있다
			- 용량 제한이 있다(주소 포함 256byte)
			- 모든 데이터가 노출된다
			- a, img, form, 주소를 직접 입력하는 등 다양한 형태로 가능하다
		- POST : 주소가 아니라 요청 내부에 숨겨서 데이터를 전달하는 방식(body)
			- 마치 편지지에 할 말을 작성하는 것과 유사하다
			- 용량 제한이 없으며 데이터가 요청 바디(편지지)에 숨겨져서 전송
			- form으로만 가능하다
	-->
	
<!-- 	<form action="/pocketmon/insertProcess"> -->
	<form action="insertProcess" method="post" autocomplete="off" enctype="multipart/form-data">
		<!-- 1. 컨테이너를 크기를 정하여 생성 -->
	    <div class="container-400">
	        <!-- 2. 배치될 항목(컴포넌트)의 줄 수 만큼 .row를 생성 -->
	        <div class="row center">
	            <h1>포켓몬 등록</h1>            
	        </div>
	        <div class="row">
	            <input type="text" name="no" placeholder="번호"
	                        class="form-input w-100" required>
	        </div>
	        <div class="row">
	            <input type="text" name="name" placeholder="이름"
	                        class="form-input w-100" required>
	        </div>
	        <div class="row">
	            <input type="text" name="type" placeholder="속성"
	                        class="form-input w-100" required>
	        </div>
	        <div class="row">
	        	<label class="form-label w-100">이미지</label>
	        	<input type="file" name="attach" class="form-input">
	        </div>
	        <div class="row right">
	            <!--
	                form 안에 있는 버튼은 전송용 버튼으로 기본 취급된다.
	                문제를 해결하기 위해서는 다음과 같이 처리한다.
	
	                1.버튼에 type을 설정해서 용도를 지정할 수 있다.
	                - type="submit" 이면 폼을 전송시키는 버튼
	                - type="button" 이면 그냥 버튼
	                2.버튼 말고 다른 태그를 사용한다
	                - a태그, input[type=button] 등을 사용할 수 있다.
	                - a태그의 display, text-align, text-decoration을 수정
	            -->
	            <button type="button" class="form-btn neutral">목록</button>
	            <button type="submit" class="form-btn positive">등록</button>
	        </div>
	    </div>
	</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


