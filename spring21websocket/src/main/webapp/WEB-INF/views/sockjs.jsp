<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>웹소켓 전체 채팅 예제(SockJS 사용)</h1>

<!-- 연결/종료 버튼 -->
<button class="btn-connect">연결</button>
<button class="btn-disconnect">종료</button>

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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		changeToDisconnect();
		
		//[1] 연결 버튼을 누르면 연결이 되도록 구현
		$(".btn-connect").click(function(){
			
			//SockJS를 사용하여 달라지는 부분(주소, 연결생성)
			const url = "${pageContext.request.contextPath}/ws/sockjs";
			window.socket = new SockJS(url);
			
			//실제로 연결이 됐는지, 끊어졌는지 알 방법이 없다
			//- 웹소켓에서 이벤트 형태로 제공한다(callback)
			window.socket.onopen = function(){
				changeToConnect();
			};
			window.socket.onclose = function(){
				changeToDisconnect();
			};
			window.socket.onerror = function(){
				changeToDisconnect();
			};
			//메세지를 수신하면 수신된 메세지로 태그를 만들어서 추가
			window.socket.onmessage = function(e){
				//console.log(e.data);
				$("<p>").text(e.data).appendTo(".message-wrapper");
			};
			
			
		});
		
		//[2] 종료 버튼을 누르면 연결이 해제되도록 구현
		$(".btn-disconnect").click(function(){
			window.socket.close();
		});
		
		//[3] 전송 버튼을 누르면 서버에게 메세지를 전송하도록 구현
		$(".btn-send").click(function(){
			const text = $(".user-input").val();
			if(text.length == 0) return;
			
			window.socket.send(text);
			$(".user-input").val("");//입력창 초기화
		});
		
		//연결 상태일 때의 화면을 만드는 함수
		function changeToConnect(){
			$(".btn-connect").prop("disabled", true);
			$(".btn-disconnect").prop("disabled", false);
			$(".user-input").prop("disabled", false);
			$(".btn-send").prop("disabled", false);
		}
		
		//종료 상태일 때의 화면을 만드는 함수
		function changeToDisconnect(){
			$(".btn-connect").prop("disabled", false);
			$(".btn-disconnect").prop("disabled", true);
			$(".user-input").prop("disabled", true);
			$(".btn-send").prop("disabled", true);
		}
	});
</script>










