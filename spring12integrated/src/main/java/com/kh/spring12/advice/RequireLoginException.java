package com.kh.spring12.advice;

//401번 상황을 대체하기 위한 예외 클래스
public class RequireLoginException extends RuntimeException {

	public RequireLoginException(String message) {
		super(message);
	}
	
}
