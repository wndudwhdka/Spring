package com.kh.spring19.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kh.spring19.component.RandomGenerator;
import com.kh.spring19.dto.CertDto;
import com.kh.spring19.repo.CertRepo;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private RandomGenerator generator;
	
	@Autowired
	private CertRepo repo;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendCert(String email) throws MessagingException, FileNotFoundException, IOException {
		//마임메세지 만들고 전달받은 이메일에 인증번호 템플릿을 전송
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, 
																StandardCharsets.UTF_8.name());
		//인증번호 추출(숫자 6자리)
		String secret = generator.number(6);
		
		//메세지 내용 설정
		helper.setTo(email);
		helper.setSubject("[KH정보교육원] 인증번호 안내");
		String content = this.loadCertTemplate(secret);
		helper.setText(content, true);
		
		//이메일 발송
		sender.send(message);
		
		//데이터베이스 등록
		CertDto certDto = new CertDto();
		certDto.setEmail(email);
		certDto.setSecret(secret);
		repo.insert(certDto);
	}

	//이 파일 내부에서만 편의상 쓰는 메소드(private)
	private String loadCertTemplate(String secret) throws FileNotFoundException, IOException {
		ClassPathResource resource = 
								new ClassPathResource("templates/cert.html");
		Scanner sc = new Scanner(resource.getFile());
		StringBuffer buffer = new StringBuffer();
		while(sc.hasNextLine()) {
			buffer.append(sc.nextLine());
		}
		sc.close();
		
		Document doc = Jsoup.parse(buffer.toString());
		Element cert = doc.getElementById("custom-email-cert");
		cert.text(secret);
		
		return doc.toString();
	}
	
}




