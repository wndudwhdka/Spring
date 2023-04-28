package com.kh.spring12.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component//독립 기능을 처리하는 도구
public class RandomComponent {
	
	private List<String> data = new ArrayList<>();
	private Random r = new Random();
	
//	Spring에서는 초기 설정을 위해 생성자를 사용하지 않는다(Java Reflection때문)
//	public RandomComponent() {}
	
//	Spring에서는 메소드에 @PostConstruct를 추가하여 생성자 역할을 수행한다
	@PostConstruct
	public void init() {
		for(char i='A'; i <= 'Z'; i++) 	data.add(String.valueOf(i));
		for(char i='a'; i <= 'z'; i++) 	data.add(String.valueOf(i));
		for(char i='0'; i <= '9'; i++) 	data.add(String.valueOf(i));
		data.add("!");
		data.add("@");
		data.add("#");
		data.add("$");
	}
	
	public String generateString(int size) {
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i < size; i++) {
			int index = r.nextInt(data.size());
			buffer.append(data.get(index));
		}
		return buffer.toString();
	}
}






