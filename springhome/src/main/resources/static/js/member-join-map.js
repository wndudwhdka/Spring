$(function(){
    //생성한 마커와 인포윈도우를 저장할 배열(나중에 지워야 되니까)
    var memory = [];//마커 저장소
    var memory2 = [];//인포윈도우 저장소

    //[1] 지도 생성
    createMap();

    //[2] 주소 입력창에 blur 이벤트 처리
    $("[name=memberDetailAddr]").blur(function(){
        clearMarker();//마커 삭제(선택)

        var address = $("[name=memberBasicAddr]").val();
        if(address.trim().length == 0) return;

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 검색
        geocoder.addressSearch(address, function(result, status) {
            console.log(result, status);
            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
                var lat = result[0].y;
                var lng = result[0].x;

                moveMap(lat, lng);
                //createMarker(lat, lng);
                createMarkerWithText(lat, lng, address, '', '');
            }
            else {//정상적으로 검색이 이루어지지 않았다면
                window.alert("검색 결과가 존재하지 않습니다");
            }
        });
    });

    //var map;

    //지도 생성 함수
    function createMap() {
        var container = document.querySelector('.map');
        // var container = $(".map").get(0);
        var options = {
            center: new kakao.maps.LatLng(37.566232, 126.977829),
            level: 3
        };
        //최상위 객체인 window에 추가해서 아무데서나 사용 가능하도록 처리
        //장점 - 호출이 편하다
        //단점 - 충돌 가능성이 있음
        window.map = new kakao.maps.Map(container, options);
    }

    //지도 이동 함수
    function moveMap(lat, lng) {
        // 이동할 위도 경도 위치를 생성합니다 
        var moveLatLon = new kakao.maps.LatLng(lat, lng);
        
        // 지도 중심을 이동 시킵니다
        // map.setCenter(moveLatLon);
        map.panTo(moveLatLon);
    }

    //마커 생성 함수
    function createMarker(lat, lng) {
        // 마커가 표시될 위치입니다 
        var markerPosition  = new kakao.maps.LatLng(lat, lng); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
        // marker.setMap(null);  

        //마커 이력 추가
        memory.push(marker);
        console.log(memory, memory.length);
    }

    //마커 + 인포윈도우 생성 함수
    function createMarkerWithText(lat, lng, text, link, linkText) {
        // 마커가 표시될 위치입니다 
        var markerPosition  = new kakao.maps.LatLng(lat, lng); 

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
        // marker.setMap(null);  

        // 인포윈도우 코드
        //var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var template = $("#info-window-template").html();
        template = template.replace("{{text}}", text);
        template = template.replace("{{link}}", link);
        template = template.replace("{{linkText}}", linkText);
        var iwContent = template;
        var iwPosition = new kakao.maps.LatLng(lat, lng); //인포윈도우 표시 위치입니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            position : iwPosition, 
            content : iwContent
        });
        
        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker); 

        //마커와 인포윈도우 이력 추가
        memory.push(marker);
        memory2.push(infowindow);
    }

    //모든 마커와 인포윈도우를 삭제
    function clearMarker() {
        for(var i=0; i < memory.length; i++) {
            memory[i].setMap(null);
        }
        for(var i=0; i < memory2.length; i++) {
            memory2[i].close();
        }
        memory = [];
        memory2 = [];
    }
});