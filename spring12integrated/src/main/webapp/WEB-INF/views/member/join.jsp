<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>가입 정보 입력 </h1>
<form action="join" method="post" enctype="multipart/form-data">
	<div class="container-400">
        <h1 class="center" >회원가입</h1>
        <div class="row">
            <h3 class="left">아이디</h3>
            <input type="text" name="memberId" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">패스워드</h3>
            <input type="password" name="memberPw" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">닉네임</h3>
            <input type="text" name="memberNick" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">전화번호</h3>
            <input type="Tel" name="memberTel" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">이메일</h3>
            <input type="email" name="memberEmail" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">생년월일</h3>
            <input type="date" name="memberBirth" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">우편번호</h3>
            <input type="text" name="memberPost" required
                    class="form-input w-100" placeholder="필수입력란입니다">
        </div>
        <div class="row">
            <h3 class="left">기본주소</h3>
            <input type="text" name="memberBasicAddr" class="form-input w-100" >
        </div>
        <div class="row">
            <h3 class="left">상세주소</h3>
            <input type="text" name="memberDetailAddr" class="form-input w-100">
        </div>
        <div>
        	<h3 class="left">프로필 이미지</h3>
        	<input type="file"name="attach">
        </div>
    
        <hr>
        <div class="row center">
        <button class="form-btn positive w-30">입력</button>
        </div>
    </div>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>