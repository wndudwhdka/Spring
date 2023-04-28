package com.hacademy.spring15;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test01 {
	
	//로그를 사용하기 위한 도구를 생성(slf4j)
	private Logger log = LoggerFactory.getLogger(Test01.class);

	@Test
	public void test() {
		log.error("ERROR 메세지");
		log.warn("WARN 메세지");
		log.info("INFO 메세지");
		log.debug("DEBUG 메세지");
	}
	
}
