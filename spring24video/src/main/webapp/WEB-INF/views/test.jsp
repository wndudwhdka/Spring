<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <head>
    <!-- <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"> -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/5.2.3/cosmo/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
    	.post{
    		border-style: none;
    	}
    </style>
  </head>
  <body>

    <!--
        container - 화면이 배치될 기본 위치

        1. container : 기본 컨테이너이며 폭에 반응하여 자동 조정(뚝뚝끊김)
        2. container-fluid : 폭의 변화에 부드럽게 반응하는 컨테이너(100%)

        row - 한 줄 영역 , col - 한 칸 영역

        사이즈 - sm, md, lg, xl
    -->
    <div class="container-fluid mt-4" id="app">

        <div class="row">
            <div class="offset-md-2 col-md-8">

<!--                 문서 제목 (Jumbotron) -->
<!--                 <div class="row text-center"> -->
<!--                     <div class="col bg-dark text-light p-4 rounded"> -->
<!--                         <h1>모달(Modal)</h1> -->
<!--                         <p>브라우저에게 차단당하지 않는 새창</p> -->
<!--                     </div> -->
<!--                 </div> -->

                <!-- 작성하고자 하는 컨텐츠 내용 -->
                <div class="row mt-4">
                    <div class="col">
                        <button type="button" class="btn btn-primary"
                            data-bs-target="#modal1" data-bs-toggle="modal">
                        	글쓰기
                        </button>
                    </div>
                </div>

            </div>
        </div>

        <!-- 
            모달은 디자인에 영향이 없으므로 아무데나 만들면 된다 
            일반적으로 화면 마지막에 만든다(처음부터 표시되지 않으니까)

            tabindex="-1"이면 탭키를 눌러서 선택이 불가능

            부트스트랩에서는 버튼등에 옵션을 지정하여 모달을 제어할 수 있다
            (프로그래밍 코드 없이도 표시/숨김 등을 설정할 수 있다)

            모달창에 아이디나 클래스같은 식별자를 표시하고 버튼에 설정으로 연결
            data-bs-target은 참조할 대상 모달창을 지정할 수 있다
            data-bs-toggle로 토글 효과를 지정할 수 있다
            data-bs-dismiss로 제거할 효과를 지정할 수 있다

            data-bs-backdrop="static"으로 배경 클릭 시 모달이 사라지지 않도록 설정
        -->


		<!-- modal, modal-dialog, modal-content, modal-header, modal-title, modal-body는 모두 -->
		<!-- bootstrap5에 사전에 정의된 모달에 관한 클래스이다. -->
		
		<script>
			// 페이지 로드
			$(function(){
				
				
				// 1. 카테고리를 저장할 변수 선언 및 카테고리 전역 변수 categori에 저장
				let categori = ""; 
				$(".modal2").click(function(){
					categori = this.innerText.trim();
					console.log(categori);
					
				});
				
				// 2. 태그를 저장할 배열 선언 및 태그 전역 변수 tag에 저장
				let tag = [];			
				// 태그 추가 버튼 클릭 시, 
				$(".tag-btn").click(function(){
					let tagInput = $(".tag-input").val();
										
					if(tagInput==""||tagInput==null) // 태그 입력창에 아무것도 안적혀 있다면
					{		
						console.log("아무것도 안적혀 있습니다");
						return
					}
					else{ // 태그 입력창에 적혀 있다면
						
						if(!tag.includes(tagInput)){ // 입력받은 태그가 중복이 아닐경우에만 
							tag.push(tagInput);	   // 없으면 indexOf가 -1을 반환
						}
						
					}
					
					$(".tag-input").val(""); // 입력창 초기화	
					let allTag = tag.join(", "); // 배열 문자열로 변환
					$(".all-tag").text(allTag); // 변환된 문자열을 출력
					
					console.log("태그에 저장된 내용은 다음과 같습니다  : "+tag);
				});
				
								
				// 3. 글 작성 버튼 클릭 시, 
				$(".write-finish").click(function(){
					
					let postText = $(".post").val();
//					모달 작성 내용 저장 변수들 - categori, tag, postText
// 					let data = {categori,tag, postText};
// 					console.log(data);
					
					
					// postDto에 삽입하기 위해 post로 송신할 JSON 객체생성
					let postDto = {
						memberId: "testid",
						postType: categori,
						postContent: postText		
					}
					
					
					console.log(postDto)
					
					$.ajax({
						  url: "http://localhost:8080/rest/post/",
						  method: "post",
						  data: postDto,
						  success: function(result) {
						    console.log(result);
						  },
						  error: function(xhr, status, error) {
						    console.log(error);
						  }
					}); 
					
					
					// 게시글 작성 이후 변수들 초기화 
					categori ="";
					$(".tag-input").val("");
					tag={};
					allTag="";
					$(".all-tag").val(""); 
					$(".post").val("");
					
					window.alert("글 게시가 완료되었습니다");					
				});
				
			});
		</script>
		
		<!----------------------------------- 네개의 모달창 구성------------------------------------------------->
		<!-- 1. 카테고리 선택 -->
        <div class="modal" tabindex="-1" role="dialog" id="modal1"
                            data-bs-backdrop="static"> <!-- static이면 모달창 외부 클릭해도 안꺼짐 -->
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                
                	<!-- header -->
                    <div class="modal-header">
                        <h5 class="modal-title">글 카테고리 설정</h5>
                    </div>
                    
                    <!-- body -->
                    <div class="modal-body">
                        <!-- 태그 버튼 선택 -->
                        <p class="text-center">무엇에 대한 글인가요?(카테고리 설정)</p>
                        <div class="row ms-5 me-5">                     
	                        <button type="button" class="col btn btn-primary modal2"
	                        	data-bs-target="#modal2" data-bs-toggle="modal">
	                        	자유
	                        </button>
	                        &nbsp; &nbsp;
	                        <button type="button" class="col btn btn-primary modal2"
	                        	data-bs-target="#modal2" data-bs-toggle="modal">
	                        	행사일정
	                        </button>
                        </div>
                        <br>
                        <div class="row ms-5 me-5">
	                        <button type="button" class="col btn btn-primary modal2"
	                        	data-bs-target="#modal2" data-bs-toggle="modal">
	                        	같이가요
	                        </button>
	                        &nbsp; &nbsp;
	                        <button type="button" class="col btn btn-primary modal2"
	                        	data-bs-target="#modal2" data-bs-toggle="modal">
	                        	펀딩
	                        </button>
                        </div>
                    </div>
                   
                    <!-- footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">닫기</button>
                    </div>
                    
                </div>      
            </div>
         </div>
       
   
         
		<!-- 2.  태그 창 (첫번 째 창에서 다음 버튼이 클릭 되었을 때, 비동기로 현존하는 이벤트 태그들을 가져옴)-->
        <div class="modal" tabindex="-1" role="dialog" id="modal2"
                            data-bs-backdrop="static">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                
                	<!-- header -->
                    <div class="modal-header">
                        <h5 class="modal-title">태그 연결</h5>
                    </div>
                    
                    <!-- body -->
                    <div class="modal-body">
                        <p class="text-center">글에 적용할 태그를 입력해주세요</p>
                        <div class="row text-center">
                     	    <div class="col-1"></div>
                        	<input type="text" class="tag-input col-7" placeholder="태그를 입력하세요">
                        	<div class="col-1"></div>
                        	<button class="col-2 tag-btn">입력</button>
                        </div>
                        <div class="row all-tag">
                        </div>                         
                    </div>
                    
                    <!-- footer -->
                    <div class="modal-footer">
                       	<button type="button" class="btn btn-primary"
                            data-bs-target="#modal3" data-bs-toggle="modal">
                        	세번째모달로
                        </button>
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">닫기</button>
                    </div>
                    
                </div>      
            </div>
         </div>
         
         
        <!-- 3. 글 및 업로드 창 (두 번째 창에서 다음 버튼이 클릭 되었을 때, 비동기로 현존하는 아이돌 태그들을 가져옴)-->
        <div class="modal" tabindex="-1" role="dialog" id="modal3"
                            data-bs-backdrop="static">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                
                	<!-- header -->
                    <div class="modal-header">
                        <h5 class="modal-title">글 작성</h5>
                    </div>
                    
                    <!-- body -->
                    <div class="modal-body">
                        <textarea class="col-12 post" style="height: 200px;" placeholder="글 작성란"></textarea>
                    </div>
                    
                    <!-- footer -->
                    <div class="modal-footer">
                    	<input type="file" multiple >
                    	<button type="button" class="btn btn-primary write-finish"
                                data-bs-dismiss="modal">작성완료</button>
                    
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">닫기</button>
                    </div>
                    
                </div>      
            </div>
         </div>
         
            
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    
  </body>
</html>