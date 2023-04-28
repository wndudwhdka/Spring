$(function(){
    //시작하자마자 서버에 비동기 통신으로 데이터를 요청
    //요청 주소 - http://localhost:8080/rest/admin/pocketmon
    //요청이 성공하면 차트를 생성
    $.ajax({
        url:contextPath+"/rest/admin/pocketmon",
        method:"get",
        success:function(response){
            //차트 생성
            var ctx = document.querySelector('#myChart');

            //new Chart(캔버스객체, 옵션);
            new Chart(ctx, {
                //type : 차트의 형태(bar/line/pie/doughnut)
                type: 'doughnut',
                //data : 차트의 구성 데이터 정보
                //- labels : 항목명(x축)
                //- datasets : 차트 데이터
                //  - label : 데이터의 이름
                //  - data : 항목별 실제 데이터(labels와 개수가 일치해야 한다)
                data: {
                    labels: response.label,
                    datasets: [
                        {
                            label: '몬스터 수',
                            data: response.data,
                            borderWidth: 1,
                            backgroundColor: ['#d6303133', '#e1705533', '#fdcb6e33', '#00b89433', '#74b9ff33', '#0984e333', '#6c5ce733'],
                            borderColor: ['#d63031', '#e17055', '#fdcb6e', '#00b894', '#74b9ff', '#0984e3', '#6c5ce7'],
                        }
                    ]
                },
                //차트의 옵션
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        },
        error:function(){
            alert("통신 오류");
        },
    });
    
});