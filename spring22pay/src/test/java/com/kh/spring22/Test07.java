package com.kh.spring22;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test07 {

	@Test
	public void test() {
		// 목표 : WebClient를 이용하여 RestTemplate과 동일한 요청 처리 
		WebClient client = WebClient.create();
		String response = client.get()
				.uri("https://www.naver.com")
				.retrieve()
				.bodyToMono(String.class)
				.block();
		log.debug("response = {}", response);
	}
	
}
