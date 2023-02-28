<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<c:choose>
	<c:when test="${boardParent==null}">
		<div class="row center">
		<h1>새글 작성</h1>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row center">
		<h1>답글 작성</h1>
		</div>
	</c:otherwise>
</c:choose> 


<form action="write" method="post">
	<div class="left container-600">
		<%-- 답글일 때는 정보가 하나 더 있어야한다.(boardParent) --%>
		<c:if test="${boardParent!=null }">
			<input type="hidden" name="boardParent" value="${boardParent}">
		</c:if>
     
        <div class="row">
            <h3 class="left">말머리</h3>
            <select name="boardHead" class="left">
		<!-- 없음을 선택하면 값이 비어서 전송되므로 DB에 null로 들어감 -->
				<option value="">없음</option>
				<c:if test="${memberLevel == '관리자'}">
				<option>공지</option>
				</c:if>
				<option>유머</option>
				<option>정보</option>
			</select>
        </div>
        
        <div class="row center">
        
        	<c:choose>
				<c:when test="${boardParent == null}">
					<h3 class="left">제목</h3>
					<input type="text" name="boardTitle" required class="form-input w-100"> <br><br>
				</c:when>
				<c:otherwise>
					<h3 class="left">제목</h3>
					<input type="text" name="boardTitle" required value="RE: " class="form-input w-100"><br><br>
				</c:otherwise>
			</c:choose>
        </div>
        
        <div class="row">
            <h3 class="left">내용</h3>
            <textarea name="boardContent" required rows="20" cols="93"></textarea>
        </div>
        <hr>
        
        <div class="row center">
        <button class="form-btn positive w-30">등록</button>
        </div>
        
    </div>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

