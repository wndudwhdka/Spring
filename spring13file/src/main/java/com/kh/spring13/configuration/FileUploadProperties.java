package com.kh.spring13.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.fileupload")
public class FileUploadProperties {
	private String path;	
}
