package com.kh.spring19;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring19.service.EmailService;

@SpringBootTest
public class SendCertMailTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	public void test() throws FileNotFoundException, MessagingException, IOException {
		emailService.sendCert("hwang8243@gmail.com");
	}
	
}
