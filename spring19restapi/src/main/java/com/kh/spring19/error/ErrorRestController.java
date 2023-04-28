package com.kh.spring19.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorRestController {
	
	//500에 대한 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> error(Exception e) {
		log.error("예외 발생", e);
		return ResponseEntity.status(500).body("Server error");
	}
	
	//404에 대한 처리
	// - 원래는 처리할 주소가 없을 경우 발생
	// - 조회 대상이 없어도 404로 변환하여 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> notFound(Exception e) {
		log.warn("404 발생", e);
		return ResponseEntity.notFound().build();
	}
	
}





