<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>내가만든 홈페이지</title>
</head>
<body>
	<!-- 
		홈페이지를 상단, 컨텐츠, 하단으로 구분하여 생성 후 불러오기
		- 상단과 하단은 별도의 페이지로 만든 뒤 불러오도록 처리
		- 이러한 페이지들을 템플릿 페이지라고 부름 
	 -->
	
	<!-- 중단 -->
	<h1>환영합니다. </h1>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>	
	
</body>
</html>