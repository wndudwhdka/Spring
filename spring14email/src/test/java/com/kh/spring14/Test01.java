package com.kh.spring14;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.kh.spring14.configuration.CustomEmailProperties;

@SpringBootTest
public class Test01 {

	@Autowired
	private CustomEmailProperties emailProperties;
	
	@Test
	public void test() {
		// 전송도구 - JavaMailSender(lmpl)
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		// 이용할 업체(Gmail에 대한 정보를 설정(호스트, 포트번호(회사별로 다름), 유저이름, 유저비번)
		sender.setHost("smtp.gmail.com"); 
		sender.setPort(587);
		sender.setUsername(emailProperties.getUsername());
		sender.setPassword(emailProperties.getPassword());
		
		//통신과 관련된 추가 설정
		// - Properties는 <String, String> 형태의 Map
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true"); // 인증 후 이용(필수)
		props.setProperty("mail.smtp.debug", "true"); // 디버깅 사용 설정(선택)
		props.setProperty("mail.smtp.starttls.enable", "true"); // TLS 사용설정(필수)
		props.setProperty("mail.smtp.ssl.protocols","TLSv1.2"); // TSL 버전 설정(필수)		
		props.setProperty("mail.smtp.ssl.trust","smtp.gmail.com"); // 신뢰할 수 있는 사용 
		
		sender.setJavaMailProperties(props);
		
		
		// 도구가 준비되었으니 메일 메세지를 작성해서 전송
		// SimpleMailMessage - 간단한 텍스트가 담긴 메세지
		// MimeMessage - 지정한 MIME 데이터가 담긴 메세지
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo("baconmasterpig@gmail.com");
		message.setSubject("이메일 발송 테스트");
		message.setText("메일내용");
		
		sender.send(message);
		
		
		
	}
}
