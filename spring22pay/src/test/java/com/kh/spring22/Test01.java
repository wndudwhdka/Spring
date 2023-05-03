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

//목표 : 카카오 단건결제 준비 요청을 전송
@Slf4j
@SpringBootTest
public class Test01 {
	
	@Test
	public void test() throws URISyntaxException{
		// 서버에서 다른 서보로 요청을 보내기 위해서는 다음 두 가지 중 하나가 필요
		//[1] RestTemplate 
		//[2] WebClient
		
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
		body.add("partner_order_id", UUID.randomUUID().toString());
		body.add("partner_user_id", UUID.randomUUID().toString());
		body.add("item_name", "최신형 맥북");
		body.add("quantity", "1");
		body.add("total_amount", "999000");
		body.add("tax_free_amount", "0");
		body.add("approval_url", "http://localhost:8080/success");
		body.add("fail_url", "http://localhost:8080/fail");
		body.add("cancel_url", "http://localhost:8080/cancel");
		
		// 헤더 + 바디
		HttpEntity entity = new HttpEntity(body,headers);
				
		// 요청 발송
		Map response = template.postForObject(uri, entity, Map.class);
		log.debug("response = {}", response); 
		log.debug("response redirect url = {}", response.get("next_redirect_pc_url"));
		
	}
}
