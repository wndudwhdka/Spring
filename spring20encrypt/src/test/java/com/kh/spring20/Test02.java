package com.kh.spring20;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring20.component.CaesarPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02 {

	@Autowired
	private CaesarPasswordEncoder encoder;
	
	@Test
	public void test() {
		String before = "hello";
		String after = encoder.encrypt(before);
		log.debug("after = {}", after);
		String restore = encoder.decrypt(after);
		log.debug("restore = {}", restore);
	}
	
}
