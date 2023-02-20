package com.kh.spring14.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.email")
public class CustomEmailProperties {

	private String username, password;
	
}
