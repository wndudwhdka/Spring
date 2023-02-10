package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

//비회원이 회원 기능에 접근하는 것을 차단하기 위한 인터셉터 
@Service
public class MemberInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		if(memberId != null ) { // 로그인상태면, 세션에 있는 memberId가 null이 아닌 경우 
		return true;
		}
		else{// 비회원이면 - 로그인 페이지로 이동시키면서 차단(리다이렉트)
		response.sendRedirect("/member/login");// 리다이렉트 코드 return "redirect:/member/login"과 같다
		return false;
		}
		 
	}
	
}
