<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/5.2.3/cosmo/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<title>Insert title here</title>
</head>

<!-- jQuery modal창 구현 -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		$("#modal-btn").click(function(){
			$(".modal").modal("show");
		
		});
	})
</script>

<body>
		
	<div class="container-fluid mt-4">
		<div class="row">
			<div class="offset-md-2 col-md-8">
				
				<!-----------------동영상, 이미지 표시 구간------------------------>
				<h1> 안녕 </h1>
				<!--  동영상이면 재생 -->
				<c:if test="${isVideo eq 'Y'}">
					<video width="640" height="360" controls>
						<source src="/download?attachmentNo=${attachmentNo}" type="${contentType}">
					</video>
				</c:if>
				<!--  이미지면 보여주기 -->
				<c:if test="${isVideo eq 'N'}">
					<img width="500" height="500" src="/download?attachmentNo=${attachmentNo}">
				</c:if>
				<h1>콘텐츠 타입은 ${contentType} 입니다.</h1>
				<!------------------------------------------------------------>
				<!------------------모달 버튼------------------->
				<div class="row mt-4">
                    <div class="col">
                        <button type="button" id="modal-btn" class="btn btn-primary modal-btn">열기</button>
                    </div>
                </div>
				<!-------------------------------------------->
				
			</div>
		</div>
		
		<!---------------모달창구간------------------>
		<div class="modal" tabindex="-1" role="dialog" id="modal02"
                            data-bs-backdrop="static">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">제목</h5>
                    </div>
                    <div class="modal-body">
                        <!-- 모달에서 표시할 실질적인 내용 구성 -->
                        <p>본문 내용</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary modal-btn"
                                data-bs-dismiss="modal">닫기</button>
                    </div>
                </div>      
            </div>
        </div>
        <!----------------------------------------->
		
	</div>	
</body>
</html>