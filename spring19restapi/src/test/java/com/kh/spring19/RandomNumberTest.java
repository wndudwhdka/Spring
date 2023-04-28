package com.kh.spring19;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring19.component.RandomGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RandomNumberTest {
	
	@Autowired
	private RandomGenerator generator;
	
	@Test
	public void test() {
		log.debug("5자리 = {}", generator.number(5));
		log.debug("6자리 = {}", generator.number(6));
		log.debug("7자리 = {}", generator.number(7));
	}
	
}






