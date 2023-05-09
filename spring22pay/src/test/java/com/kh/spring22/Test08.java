package com.kh.spring22;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.spring22.vo.KakaoPayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test08 {

	@Test
	public void test() {
		// 기분 URL을 생성할 수 있다.
		// WebClient client = WebClient.create("https://kapi.kakao.com");
		
		// 헤더까지 지정하여 생성
		WebClient client = WebClient.builder()
				.baseUrl("https://kapi.kakao.com")
				.defaultHeader("Authorization", null)
				.defaultHeader("Content-type", null)
			.build();
		// client를 이용하여 요청 및 수신을 할 대 클래스를 지정하여 처리할 수 있따.
		// - form-data와 JSON 중에서 보내는 유형을 선택할 수 있다.
		// - JSON은 클래스 자동변환을 제공한다.
		// - form-data는 MultiValueMap 또는 별도의 등록 클래스를 사용해야 함
		
		
		// 데이터 준비
		String partner_order_id = UUID.randomUUID().toString();
		String partner_user_id = UUID.randomUUID().toString();
		String item_name = "최신형 맥북";
		int quantity = 1; 
		int total_amount = 990043;
		
		// 바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");
		body.add("partner_order_id", partner_order_id);
		body.add("partner_user_id", partner_user_id);
		body.add("item_name", item_name);
		body.add("quantity", String.valueOf(quantity));
		body.add("total_amount", String.valueOf(total_amount));
		body.add("tax_free_amount", "0");
		body.add("approval_url", "http://localhost:8080/success");
		body.add("fail_url", "http://localhost:8080/fail");
		body.add("cancel_url", "http://localhost:8080/cancel");
				
		
		
		KakaoPayReadyResponseVO response = client.post()
				.uri("/v1/payment/ready")
				.bodyValue(body)
				.retrieve()
				.bodyToMono(KakaoPayReadyResponseVO.class)
				.block();
		log.debug("response = {}", response); 
		
	}
	
}
