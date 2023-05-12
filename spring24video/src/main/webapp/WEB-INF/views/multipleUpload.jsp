<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!--summernote cdn-->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<script>
   $(function(){
       $('[name=postContent]').summernote({
            placeholder: '내용을 작성하세요.',
            tabsize: 2, // 탭키를 누르면 띄어쓰기 몇 번 할지
            height: 120, // 최초 표시될 높이(px)
            toolbar: [ // 메뉴 설정
               ['style', ['style']],
                 ['font', ['bold', 'underline', 'clear']],
                 ['color', ['color']],
                 ['para', ['ul', 'ol', 'paragraph']],
                 ['table', ['table']],
                 ['insert', ['link', 'picture']],
            ],
            callbacks: {
                   onImageUpload: function (files, editor, welEditable) {
                       // 이미지 파일 서버에 업로드
                       var data = new FormData();
                       data.append("file", files[0]);

                       $.ajax({
                           data: data,
                           type: "POST",
                           url: "",
                           cache: false,
                           contentType: false,
                           processData: false,
                           success: function (imageUrl) {
                               // 이미지 파일 삽입
                               $('#summernote').summernote('editor.insertImage', imageUrl);
                           },
                           error: function (xhr, textStatus, errorThrown) {
                               console.error(xhr.responseText);
                           }
                       });
                   }
               }
          });
    });
</script>

<div style="width:600px";>
   <h2>게시글 작성</h2>
   <form action="write2" method="post" enctype="multipart/form-data">
      제목 : <input type="text" name="fundTitle"><br><br>
      시작일 : <input type="date" name="postStart"><br><br>
      종료일 : <input type="date" name="postEnd"><br><br>
      목표 금액 : <input type="text" name="fundGoal"><br><br>
<!--       후원자 수 : <input type="text" name="fundSponsorCount"><br><br> -->
      펀딩 상태 : <input type="text" name="fundState"><br><br>
      내용 : <textarea name="postContent"></textarea><br><br>
<!--       이미지 : <input type="file" name="attach"> -->
      이미지 : <input type="file" name="attaches" multiple>
      <button type="submit">글쓰기</button>
   </form>
</div>
   
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>