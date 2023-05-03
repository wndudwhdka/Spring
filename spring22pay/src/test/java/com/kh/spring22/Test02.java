package com.kh.spring22;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

//목표 : 전송한 단편 결제 요청을 결제 처리하고 해당 결제건에 대한 데이터들 출력
@Slf4j
@SpringBootTest
public class Test02 {
	
	@Test
	public void test() throws URISyntaxException{
		// 서버에서 다른 서보로 요청을 보내기 위해서는 다음 두 가지 중 하나가 필요
		//[1] RestTemplate 
		//[2] WebClient
		
		// 데이터 준비
		String partner_order_id = UUID.randomUUID().toString();
		String partner_user_id = UUID.randomUUID().toString();
		String item_name = "최신형 맥북";
		int quantity = 1; 
		int total_amount = 990043;
		
		// 전송 도구 생성 
		RestTemplate template = new RestTemplate();
		
		// 주소 설정
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 7a9165f5a9ac10a044b4d0c6bda49fcb");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
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
		
		// 헤더 + 바디
		HttpEntity entity = new HttpEntity(body,headers);
				
		// 요청 발송
		Map response = template.postForObject(uri, entity, Map.class); 
		log.debug("partner_order_id = {}", partner_order_id);
		log.debug("partner_user_id = {}", partner_user_id);
		log.debug("tid = {}", response.get("tid"));
		log.debug("url = {}", response.get("next_redirect_pc_url"));
		
	}
}
