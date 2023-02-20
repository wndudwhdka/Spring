package com.kh.spring13;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring13.configuration.AcademyProperties;

@SpringBootTest // String에 있는 모든 도구를 가져와서 사용할 수 있음
public class Test01 {
	
	@Autowired
	private AcademyProperties properties;
	
	@Test
	public void test() {
		System.out.print(properties);
	}
	
}
