package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 인터셉터(interceptor)
 * 스프링의 실행과저에 개입하여 변화를 일으킬 수 있는 도구
 * 등록만으로 사용할 수 없음 
 * 상속을 통한 자격 획득 및 스프링에 사용 설정을 해야 함
 * 어떤 방식으로 접근을 차단할지 정의
 * HandlerInterceptor 상속
 * 다음 메소드를 재정의하여 사용
 * -preHandle - 컨트롤러가 실행되기 직전 시점에 간섭
 * -postHandle - 컨트롤러 실행 직후 시점에 간섭(화면은 아직 생성되지 않음)
 * -afterCompletion - 화면까지 다 생성된 후 간섭
 */

// 인터페이스 임에도 메소드를 정의 안해도 된다(업그레이드 됨 ㅎㅎ)
@Service
public class TestInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// HttpSession은 HttpServletRequest 객체 내부에 포함되어 있다.
		// request는 요청 정보가 담긴 객체이고
		// response는 응답 정복 담긴 객체이다.
		
		// interceptor는 만들어도 등록을 안하면 안된다. 
//		System.out.println("안녕");
		//return false면 접근차단
		//return true면 접근허용
		return true;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, // 요청 객체
			HttpServletResponse response,  // 응답 객체
			Object handler, // 실행할 컨트롤러 정보 
			ModelAndView modelAndView) // 컨트롤러에서 반환한 데이터+화면정보  
					throws Exception {
		
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, // 요청 객체
			HttpServletResponse response,  // 응답 객체
			Object handler,  // 실행한 컨트롤러 정보
			Exception ex) // 예외 정보 발생 안했으면 null 
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
