package com.kh.spring20.component;

public interface CaesarPasswordEncoder {
	String encrypt(String origin);
	String decrypt(String value);
}
