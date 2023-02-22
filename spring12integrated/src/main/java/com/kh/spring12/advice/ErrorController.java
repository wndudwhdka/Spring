package com.kh.spring12.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

//예외만 전문적으로 처리하는 컨트롤러(사용자가 부를 수 없음)
//-(주의) 컨트롤러만 감시가 가능하다 
// 주소를 따로 주지 않는다(오류 발생 시 표시하는 것이기 때문)
// 405 - 처리 가능한 메소드가 없음

//@ControllerAdvice(annotations = {Controller.class}) @Controller에 대한 catch 블럭
//@ControllerAdvice(basePackages = {"com.kh.spring12.controller"})
@ControllerAdvice //프로젝트 전체에 대한 catch 블럭
public class ErrorController {

	
	// 마치 catch 블록을 만들듯이 메소드를 구현
	// -@ExceptionHandler(처리할 예외 클래스 정보)
	// - 컨트롤러처럼 특정 페이지를 보여주거나 Redirect 처리를 하능 등이 기능
	// - 컨트롤러에서 제공받는 도구들을 모두 사용할 수 있다. 
	@ExceptionHandler(Exception.class)
	public String error(Exception ex) { 
		ex.printStackTrace();
		return "/WEB-INF/views/error/sorry.jsp";
	}	

	// 404 예외만 따로 처리할 수 있도록 추가 메소드를 구성
	// - 이 예외는 다른 예외로 변경할 수 없다. 
	@ExceptionHandler(NoHandlerFoundException.class)
	public String notFound(Exception ex) {
		return "/WEB-INF/views/error/404.jsp";
	}
	
	// 403 - 권한이 없음
	@ExceptionHandler(RequirePermissionException.class)
	public String forbiden(Exception ex) {	
		return "/WEB-INF/views/error/403.jsp";
	}

	// 401 - 자격이 없음 우리가 ㅏㅁㄴ든 RequireLoginExceptio으로 대체하여 처리!
	// 사용자가 봐야 하는 페이지는 로그인 페이지이다
	@ExceptionHandler(RequireLoginException.class)
	public String unAutorized(Exception ex) {
		// return "/WEB-INF/views/member/login.jsp";  //주소는 유지하고 화면만 변경 (forward) 
		return "redirect:/member/login"; // 재접속을 지시하여 주소도 변경 (redirect)
	}
	
}
