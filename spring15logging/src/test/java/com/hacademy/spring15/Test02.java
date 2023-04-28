package com.hacademy.spring15;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

//롬복이 Logger를 자동 생성해준다
@Slf4j
@SpringBootTest
public class Test02 {
	
	//private static final Logger log = LoggerFactory.getLogger(Test02.class);
	
	@Test
	public void test() {
		log.debug("롬복은 참 좋은것 같아요");
		
		log.debug("10 + 20 = 30");
		
		int a = 10, b = 20, c = a+b;
		log.debug(a + " + " + b + " = " + c);
		
		log.debug("{} + {} = {}", a, b, c);
	}
}



