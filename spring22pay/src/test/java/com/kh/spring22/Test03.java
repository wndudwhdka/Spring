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

//목표 : Test03완료 메세지 전송 
@Slf4j
@SpringBootTest
public class Test03 {
	
	@Test
	public void test() throws URISyntaxException {
		// 데이터 준비
		String partner_order_id = "b999d4b0-b3e9-43f5-b9e1-e7acc888709c";
		String partner_user_id = "2686e622-83fd-493a-8a9e-458bd923e393";
		String tid = "T450a56b5b8c2c02ac36";
		String pg_token = "144b45ed57ac4a88b37e"; 
		
		// 전송 도구 생성 
		RestTemplate template = new RestTemplate();
		
		// 주소 설정
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		
		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 7a9165f5a9ac10a044b4d0c6bda49fcb");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 설정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");
		body.add("partner_order_id", partner_order_id);
		body.add("partner_user_id", partner_user_id);
		body.add("tid", tid);
		body.add("pg_token", pg_token);
		
		// 헤더 + 바디
		HttpEntity entity = new HttpEntity(body,headers);
				
		// 요청 및 응답 저장
		Map response = template.postForObject(uri, entity, Map.class); 
		log.debug("response = {}", response);
		
		
		log.debug("partner_order_id = {}", partner_order_id);
		log.debug("partner_user_id = {}", partner_user_id);
		log.debug("tid = {}", response.get("tid"));
		log.debug("url = {}", response.get("next_redirect_pc_url"));
		
	}
}
