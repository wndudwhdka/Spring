<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style>
		.container-400 {
    		width:400px;
    		margin:0 auto;
		}
		.center {text-align: center;}
		.left {text-align: left;}
		.right {text-align: right;}
		.row {
 		   	margin:10px 0px;
		}
		.form-input {
		    font-size: 20px;
		    padding: 0.5em;
		    outline: none;/*선택 시 강조 효과 제거*/
		    border: 1px solid #636e72;
		    border-radius: 0.1em;
		}
		.w-100 {width:100%;}
		.w-100 {width:30%;}
		.form-btn.positive {
		    background-color: #0984e3;
		    border-color: #0984e3;
		    color: white;
		}
	</style>
</head>
<body>
	<h1>가입 정보 입력 </h1>
	<form action="join" method="post">
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
	        <hr>
	        <div class="row center">
	        <button class="form-btn positive w-30">입력</button>
	        </div>
	    </div>
	</form>
</body>
</html>