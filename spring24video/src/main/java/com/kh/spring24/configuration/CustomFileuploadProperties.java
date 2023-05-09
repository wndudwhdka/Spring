package com.kh.spring24.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.filedir")
public class CustomFileuploadProperties {
    private String path;
}
