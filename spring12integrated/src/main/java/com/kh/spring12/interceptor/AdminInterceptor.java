package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring12.advice.RequirePermissionException;

@Service
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String memberLevel = (String)session.getAttribute("memberLevel");
		
		if(memberLevel != null && memberLevel.equals("관리자")) // 없는 경우를 반드시 먼저 검사해야 한다. 
		{
			return true; 
		}
		else // 레벨이 관리자가 아니거나 아이디가 미접속인 경우 
		{
			// response.sendError(403);
			// response.sendError(HttpStatus.FORBIDDEN.value());
			//return false;
			//response.sendError(403, "error가 발생했습니다.");
			throw new RequirePermissionException("관리자만 이용 가능합니다"); 
		}
	}
}
