package com.kh.spring22;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring22.service.KakaoPayService;
import com.kh.spring22.vo.KakaoPayApproveRequestVO;
import com.kh.spring22.vo.KakaoPayApproveResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test05 {

	@Autowired
	private KakaoPayService kakaoPayService;
	
	@Test
	public void test( ) throws URISyntaxException {
		
		KakaoPayApproveRequestVO vo = new KakaoPayApproveRequestVO();
		vo.setPartner_order_id("701baa2f-546f-40df-be39-4f269ed2a0a3");
		vo.setPartner_user_id("5b272924-7348-4dd2-8ea7-5c8d5734e686");
		vo.setTid("T451e5165b8c2c02b903");
		vo.setPg_token("19e4616fcb5c3ae9e719");
		
		KakaoPayApproveResponseVO response = kakaoPayService.approve(vo);
		log.debug("response = {}", response);
	}
	
}