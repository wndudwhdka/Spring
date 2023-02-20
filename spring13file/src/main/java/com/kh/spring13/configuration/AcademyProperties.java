package com.kh.spring13.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

//application.properties에 작성된 academy로 시작하는 정보를 불러오는 클래스 
// - 등록은 @Component 또는 @Service로 한다.
@Data
@Component
@ConfigurationProperties(prefix = "custom.academy") // 내가 만든 부분은 custom이 붙는다. 
public class AcademyProperties {
	
	private String name; // academy.name
	private String location; // academy.location
	private int since; // academy.since
	
}
