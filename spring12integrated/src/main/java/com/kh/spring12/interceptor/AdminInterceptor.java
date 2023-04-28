package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring12.advice.RequirePermissionException;

//관리자만 접근하도록 하는 인터셉터
@Service
public class AdminInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String memberLevel = (String)session.getAttribute("memberLevel");
		
//		(주의) 없는 경우를 반드시 먼저 검사해야 한다
		if(memberLevel != null && memberLevel.equals("관리자") ) {
			return true;
		}
		else {
//			response.sendError(403);
//			response.sendError(HttpStatus.FORBIDDEN.value());
//			return false;
			throw new RequirePermissionException("관리자만 이용 가능합니다");
		}
	}
}
