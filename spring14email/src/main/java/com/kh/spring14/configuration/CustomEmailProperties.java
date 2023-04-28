package com.kh.spring14.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

//application.properties에 작성된 이메일 정보를 불러오는 파일
@Data
@Component
@ConfigurationProperties(prefix = "custom.email")
public class CustomEmailProperties {
	private String host;
	private int port;
	private String username, password;
}
