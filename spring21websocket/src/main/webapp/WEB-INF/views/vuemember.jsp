<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VueJS</title>

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
</head>
<body>
    <!--
        VueJS는 제어 영역을 반드시 설정해야 한다
    -->
    <div id="app">
        
        <h1>Vue 채팅 클라이언트 예제(회원전용 채팅)</h1>
        
        <input type="text" v-model="text" v-on:input="text=$event.target.value">
        <button v-on:click="sendMessage">전송</button>
        
        <hr>
        
        <div class="message-wrapper">
        	<div class="message" v-for="(message, index) in messageList"
        															:key="index">
        		<div>
        			{{message.memberId}}
        			<div v-if="message.memberId == memberId">(내꺼)</div>
        		</div>
        		<div>{{message.content}}</div>
<!--         		<div>{{message.time}}</div> -->
        		<div>{{timeFormat(message.time)}}</div>
        	</div>
        </div>
        
    </div>

    <!--
        VueJS는 Lazy loading 방식으로 제어 영역에 대한 프로그래밍 구현
        무조건 최신버전
        <script src="https://unpkg.com/vue@next"></script>
    -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/locale/ko.min.js"></script>
    <script src="https://unpkg.com/vue@3.2.36"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
    <script>
        Vue.createApp({
            data(){
                return {
                    text:"",//사용자가 입력하는 내용
                    messageList:[],//채팅 기록
                    memberId:"${sessionScope.memberId}",//나의 아이디
                    socket:null,//웹소켓 연결 객체
                };
            },
            methods:{
            	connect(){
            		const url = "${pageContext.request.contextPath}/ws/vuemember";
            		this.socket = new SockJS(url);
            		
            		const app = this;
            		this.socket.onopen = function(){
            			app.openHandler();
            		};
            		this.socket.onclose = function(){
            			app.closeHandler();
            		};
            		this.socket.onerror = function(){
            			app.errorHandler();
            		};
            		this.socket.onmessage = function(e){
            			app.messageHandler(e);
            		};
            	},
            	openHandler(){
            		
            	},
            	closeHandler(){
            		
            	},
            	errorHandler(){
            		
            	},
            	messageHandler(e){
            		this.messageList.push(JSON.parse(e.data));
            	},
            	sendMessage() {
            		if(this.text.length == 0) return;
            		this.socket.send(this.jsonText);
            		this.text = "";
            	},
            	timeFormat(time) {
            		return moment(time).format("A h:mm");
            	}
            },
            computed:{
            	jsonText() {
            		return JSON.stringify({content : this.text});
            	},
            },
            created(){
            	//웹소켓 연결 코드
            	this.connect();
            }
        }).mount("#app");
    </script>
</body>
</html>