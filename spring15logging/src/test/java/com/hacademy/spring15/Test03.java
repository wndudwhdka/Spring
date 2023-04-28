package com.hacademy.spring15;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test03 {
	
	@Test
	public void test() {
		for(int i=1; i <= 10000000; i++) {
			log.debug("Logging Test {}", i);
		}
	}
	
}
