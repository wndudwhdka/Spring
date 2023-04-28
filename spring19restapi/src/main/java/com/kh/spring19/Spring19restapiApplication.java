package com.kh.spring19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//이 프로그램에서는 스케쥴링 기능을 쓸것이다
@SpringBootApplication
public class Spring19restapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring19restapiApplication.class, args);
	}

}
