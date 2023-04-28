package com.kh.spring14;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

//목표 : src/main/resources/templates/email.html을 읽어와서 메일로 발송
@SpringBootTest
public class Test05 {

	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void test() throws MessagingException, FileNotFoundException, IOException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(message, false, "UTF-8");
		
		helper.setTo("hwang8243@gmail.com");
		helper.setSubject("마임메세지 템플릿 테스트");
		
		//email.html의 내용을 읽어와서 첨부
		//- ClassPathResource는 주어진 경로 앞에 classpath:/를 첨부하여 탐색
		ClassPathResource resource = 
						new ClassPathResource("templates/email2.html");
		Scanner sc = new Scanner(resource.getFile());
		StringBuffer buffer = new StringBuffer();
		
		while(sc.hasNextLine()) {//읽을 줄이 남았다면
			buffer.append(sc.nextLine());//한 줄을 읽어서 buffer에 추가해라
		}
		
		//불러온 내용을 문자열로 저장
		String text = buffer.toString();
		
		//text를 HTML로 해석하여 원하는 영역을 찾아 값을 교체(Jsoup 사용)
		Document doc = Jsoup.parse(text);
		Element title = doc.getElementById("custom-email-title");
		title.text("빚 받으러 왔습니다");
		
		Element target = doc.getElementById("custom-email-target");
		target.text("피카츄");
		
		Element link = doc.getElementById("custom-email-link");
		link.text("계좌 이체 페이지로 이동");
		link.attr("href", "https://www.google.com");
		
		//변경한 내용을 첨부
		helper.setText(doc.toString(), true);
		
		sender.send(message);//전송
	}
	
}



