package com.kh.spring20;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test05Module {
	
	//JUnit에서는 초기화를 위한 메소드를 제공(@BeforeEach)
	//JUnit에서는 마무리를 위한 메소드를 제공(@AfterEach)
	
	
	PasswordEncoder encoder;
	
	@BeforeEach
	public void before() {
//		StandardPasswordEncoder - 변형 SHA-256 알고리즘 암호화 도구
//		encoder = new StandardPasswordEncoder();
		
//		Pbkdf2PasswordEncoder - 의도적으로 암호화를 느리게 하는 도구
//		encoder = new Pbkdf2PasswordEncoder();
		
//		BCryptPasswordEncoder - 시간을 고려하여 지속적으로 변화하는 암호를 만드는 도구
		encoder = new BCryptPasswordEncoder();
	}

	@Test
	public void test() {
		String origin = "hello everyone";
		String encrypt = encoder.encode(origin);
		log.debug("origin = {}", origin);
		log.debug("encrypt = {}", encrypt);
	}
	
}
