package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 인터셉터(Interceptor)
 *	- 스프링의 실행 과정에 개입하여 변화를 일으킬 수 있는 도구
 *	- 등록만으로 사용할 수 없음
 *	- 상속을 통한 자격 획득 및 스프링에 사용 설정을 해야 함
 *	- HandlerInterceptor 상속
 *	- 다음 메소드를 재정의하여 사용
 *		- preHandle - 컨트롤러가 실행되기 직전 시점에 간섭
 *		- postHandle - 컨트롤러 실행 직후 시점에 간섭(화면은 아직 생성되지 않음)
 *		- afterCompletion - 화면까지 다 생성된 후 간섭
 */
@Service
public class TestInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(
			HttpServletRequest request, //요청 정보가 담긴 객체
			HttpServletResponse response, //응답 정보가 담긴 객체
			Object handler)//실행될 대상 컨트롤러 정보
			throws Exception {
//		인터셉터는 컨트롤러 처럼 선언한다고 주지 않는다
//		- HttpSession은 HttpServletRequest 객체 내부에 포함되어 있다
		System.out.println("테스트 인터셉터!");
//		return false;//차단
		return true;//통과
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, //요청 객체
			HttpServletResponse response, //응답 객체
			Object handler,//실행한 컨트롤러 정보
			ModelAndView modelAndView) //컨트롤러에서 반환한 데이터+화면정보
					throws Exception {
		
	}
	
	@Override
	public void afterCompletion(
				HttpServletRequest request, //요청 객체
				HttpServletResponse response, //응답 객체
				Object handler, //실행한 컨트롤러 정보
				Exception ex)//예외 정보(발생 안했으면 null)
			throws Exception {
		
	}
}




