package com.kh.spring12.advice;

// 403 번 대신 사용할 예외 클래스
// - responde.sendError(403) 대신 throw new RequiredPermissionException() 사용
// 이렇게 해야 @ControllerAdvice에서 처리가 가능하기 때문 
// RuntimeException을 상속받으면 따로 예외 전가를 하지 않아도 된다.

public class RequirePermissionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // 버전번호를 붙이지 않으면 랜덤버전

	public RequirePermissionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
