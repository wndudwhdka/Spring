<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	.map {
		width:100%;
		height:300px;
	}
	.info-window {
		
	}
</style>
<!-- kakao map cdn -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d49f07bfcf20a8d717a28a819c2892b&libraries=services"></script>
<script type="text/javascript" src="/static/js/member-join-map.js"></script>
<script type="text/template" id="info-window-template">
	<div class="info-window p-10">
		<span class="text">{{text}}</span>
		<a class="link" href="{{link}}">{{linkText}}</a>
	</div>
</script>    

<!-- 다음 우편 API 사용을 위한 CDN -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/gh/hiphop5782/daum-post-api@latest/find-address.min.js"></script> -->
<script src="/static/js/find-address.min.js"></script>

<script src="/static/js/member-join.js"></script>

<form class="join-form" action="join" method="post" enctype="multipart/form-data" autocomplete="off">
<div class="container-600">
	<div class="row center">
		<h2>가입 정보 입력</h2>
	</div>
	<div class="row">
		<label>아이디<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="memberId" class="form-input w-100">
		<div class="valid-message">사용 가능한 아이디입니다</div>
		<div class="invalid-message">아이디는 영문소문자로 시작하며 숫자를 포함한 8~20자로 작성하세요</div>
		<div class="invalid-message2">이미 사용중인 아이디입니다</div>
	</div> 
	<div class="row">
		<label>비밀번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" name="memberPw" class="form-input w-100">
		<div class="valid-message">올바른 비밀번호 형식입니다</div>
		<div class="invalid-message">영문 대/소문자, 숫자, 특수문자를 반드시 포함하여 8~16자로 작성하세요</div>
	</div>
	<div class="row">
		<label>비밀번호 확인<i class="fa-solid fa-asterisk"></i></label>
		<input type="password" id="passwordRe" class="form-input w-100">
		<div class="valid-message">비밀번호가 일치합니다</div>
		<div class="invalid-message">비밀번호가 일치하지 않습니다</div>
		<div class="invalid-message2">비밀번호를 먼저 작성하세요</div>
	</div>
	<div class="row">
		<label>닉네임<i class="fa-solid fa-asterisk"></i></label>
		<input type="text" name="memberNick" class="form-input w-100">
		<div class="valid-message">사용 가능한 닉네임입니다</div>
		<div class="invalid-message">닉네임은 한글 또는 숫자 2~10글자로 작성하세요</div>
		<div class="invalid-message2">이미 사용중인 닉네임입니다</div>
	</div>
	<div class="row">
		<label>전화번호<i class="fa-solid fa-asterisk"></i></label>
		<input type="tel" name="memberTel" class="form-input w-100" placeholder="대시(-)를 제외하고 작성">
		<div class="invalid-message">올바른 휴대전화번호가 아닙니다</div>
	</div>
	<div class="row">
		<label class="form-label w-100">생년월일<i class="fa-solid fa-asterisk"></i></label>
		<input type="date" name="memberBirth" class="form-input">
	</div>
	<div class="row">
		<label>이메일</label>
		<input type="email" name="memberEmail" class="form-input w-100">
	</div>
	<div class="row">
		<label class="form-label w-100">주소</label>
		<input type="text" name="memberPost" class="form-input" placeholder="우편번호" readonly>
		<button type="button" class="form-btn neutral find-address-btn">우편번호 찾기</button>
		<button type="button" class="form-btn negative clear-address-btn">
			<i class="fa-solid fa-eraser"></i>
		</button>
	</div>
	<div class="row">
		<input type="text" name="memberBasicAddr" class="form-input w-100" placeholder="기본주소" readonly>
	</div>
	<div class="row">
		<input type="text" name="memberDetailAddr" class="form-input w-100" placeholder="상세주소">
		<div class="invalid-message">주소는 비워두거나 모두 작성해야 합니다</div>
	</div>
	<div class="row">
		<div class="map"></div>
	</div>
	<div class="row">
		<label class="form-label w-100">프로필 이미지</label>
		<input type="file" name="attach" class="form-input">
	</div>
	<div class="row">
		<button type="submit" class="form-btn positive w-100">가입</button>
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>