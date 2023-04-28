package com.kh.spring14;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class Test02 {

	//미리 만든 @Bean을 불러와서 사용할 수 있다.
	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void test() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("hwang8243@gmail.com");
		message.setSubject("테스트 이메일");
		message.setText("인증번호 : oooooo");
		
		sender.send(message);
	}
	
}






