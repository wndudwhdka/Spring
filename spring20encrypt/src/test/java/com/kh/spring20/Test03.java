package com.kh.spring20;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

//목표 : xor 암호화(비트연산)
@Slf4j
@SpringBootTest
public class Test03 {

	@Test
	public void test( ) {
		int before = 15;
		int offset = 7;
		int after = before ^ offset;
		log.debug("after = {}", after);
		int restore = after ^ offset;
		log.debug("restore = {}", restore);
		
		String originStr = "hello";
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i < originStr.length(); i++) {
			char ch = originStr.charAt(i);
			ch ^= offset;
			buffer.append(ch);
		}
		String afterStr = buffer.toString();
		log.debug("afterStr = {}", afterStr);
	}
	
}


