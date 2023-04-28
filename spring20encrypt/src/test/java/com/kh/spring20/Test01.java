package com.kh.spring20;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01 {

	@Test
	public void test() {
		/* 
			암호화(Encryption)란?
			- 원하는 사람만 정보를 사용할 수 있도록 변조하는 것
			- 다시 복구할 수 있는 양방향 암호화와 복구가 불가능한 단방향 암호화가 존재
		 */
		
		//시저(caeser) 암호화
		//- 값에 특정 offset을 더하거나 빼서 변조하는 암호화 방식
		
		//숫자일 경우
		int before = 10;
		int offset = 3;
		
		int after = before + offset;
		log.debug("after = {}", after);
		
		//문자열일 경우
		String str = "hello";
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i < str.length(); i++) {
			char ch = str.charAt(i);
			ch += offset;
			buffer.append(ch);
		}
		
		String afterStr = buffer.toString();
		log.debug("afterStr = {}", afterStr);
	}
	
}








