<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
	.message {
		border:1px solid gray;
		padding:10px;
	}
</style>

<h1>채널 예제 4번(채널간 이동)</h1>
<h2>${sessionScope.memberId}, ${sessionScope.memberLevel}</h2>

<hr>
<!-- 채널 선택 화면 -->
<button id="channel-1">1번 채널</button>
<button id="channel-2">2번 채널</button>
<button id="channel-3">3번 채널</button>
<hr>

<!-- 메세지 입력창+전송버튼 -->
<input type="text" class="user-input">
<button class="btn-send">전송</button>

<hr>

<!-- 메세지가 표시될 공간 -->
<div class="message-wrapper"></div>


<!-- 
	SockJS를 사용하려면 sockjs-client 라이브러리의 의존성을 추가해야 한다
	[1] 주소를 http로 써도 된다(알아서 ws로 바꿔준다)
	[2] WebSocket을 지원하지 않는 브라우저는 풀링 방식으로 자동 변환한다
	[3] 주기적으로 생존여부를 체크해준다(heartbeat)
-->
<script type="text/template" id="message-template">
<div class="message">
	<h2 class="memberId">보낸사람</h2>
	<p class="content">내용</p>
	<span class="time">HH:mm</span>
</div>
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/locale/ko.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		changeToDisconnect();
		
		//시작하자마자 연결
			
		//SockJS를 사용하여 달라지는 부분(주소, 연결생성)
		const url = "${pageContext.request.contextPath}/ws/channel4";
		window.socket = new SockJS(url);
		
		//실제로 연결이 됐는지, 끊어졌는지 알 방법이 없다
		//- 웹소켓에서 이벤트 형태로 제공한다(callback)
		window.socket.onopen = function(){
			changeToConnect();
			$("<p>").text("서버에 연결되었습니다")
						.appendTo(".message-wrapper");
		};
		window.socket.onclose = function(){
			changeToDisconnect();
			$("<p>").text("서버와의 연결이 종료되었습니다")
						.appendTo(".message-wrapper");
		};
		window.socket.onerror = function(){
			changeToDisconnect();
			$("<p>").text("서버와의 연결 오류가 발생했습니다")
						.appendTo(".message-wrapper");
		};
		//메세지를 수신하면 수신된 메세지로 태그를 만들어서 추가
		window.socket.onmessage = function(e){
			//수신한 데이터(e.data)가 JSON 문자열 형태이므로 해석 후 처리
			const data = JSON.parse(e.data);
			const time = moment(data.time).format("HH:mm");
			//const time = moment(data.time).fromNow();
			
			var template = $("#message-template").html();
			var html = $.parseHTML(template);
			$(html).find(".memberId").text(data.memberId);
			$(html).find(".content").text(data.content);
			$(html).find(".time").text(time);
			
			switch(data.memberLevel) {
			case "우수회원": 
				$(html).find(".memberId").css("color", "dodgerblue"); 
				break;
			case "관리자":
				$(html).find(".memberId").css("color", "firebrick");
				break;
			}
			
			$(".message-wrapper").append(html);
		};
			
		
		//페이지 나가면 종료이므로 처리하지 않음
		
		//전송 버튼을 누르면 서버에게 메세지를 전송하도록 구현
		$(".btn-send").click(function(){
			const text = $(".user-input").val();
			if(text.length == 0) return;
			
			//window.socket.send(text); //일반 텍스트
			
			//<자바스크립트에서 JSON을 처리하는 명령>
			//JSON.stringify(객체) ---> 객체를 JSON 문자열로 변환
			//JSON.parse(JSON문자열) ---> JSON 문자열을 객체로 변환
			const data = {type:1, content:text};
			window.socket.send(JSON.stringify(data));
			
			$(".user-input").val("");//입력창 초기화
		});
		
		//연결 상태일 때의 화면을 만드는 함수
		function changeToConnect(){
			$(".user-input").prop("disabled", false);
			$(".btn-send").prop("disabled", false);
		}
		
		//종료 상태일 때의 화면을 만드는 함수
		function changeToDisconnect(){
			$(".user-input").prop("disabled", true);
			$(".btn-send").prop("disabled", true);
		}
		
		//버튼을 누르면 채널 이동 메세지를 전송
		//(형태) {type : 2 , room : "방이름"}
		$("#channel-1").click(function(){
			const data = {type:2, room:"1"};
			window.socket.send(JSON.stringify(data));
		});
		$("#channel-2").click(function(){
			const data = {type:2, room:"2"};
			window.socket.send(JSON.stringify(data));
		});
		$("#channel-3").click(function(){
			const data = {type:2, room:"3"};
			window.socket.send(JSON.stringify(data));
		});
	});
</script>









