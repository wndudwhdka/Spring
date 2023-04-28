package com.kh.spring20.component;

import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class CaesarPasswordEncoderImpl implements CaesarPasswordEncoder{
	
	@Setter
	private int offset = 3;

	@Override
	public String encrypt(String origin) {
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i < origin.length(); i++) {
			char ch = origin.charAt(i);
			ch += offset;
			buffer.append(ch);
		}
		return buffer.toString();
	}

	@Override
	public String decrypt(String value) {
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i < value.length(); i++) {
			char ch = value.charAt(i);
			ch -= offset;
			buffer.append(ch);
		}
		return buffer.toString();
	}
	
}
