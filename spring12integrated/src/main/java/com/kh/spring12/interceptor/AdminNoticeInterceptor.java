package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AdminNoticeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 전송방식이 GET이면 통과
		if(request.getMethod().equalsIgnoreCase("get")) {
			return true; 
		}
				
		// 남은 요청은(POST)은 검사 
		String boardHead = request.getParameter("boardHead");
		boolean isNotice = boardHead != null && boardHead.equals("공지"); 
		
		if(isNotice) { // 공지를 등록하력 한다면
			// 관리자 검사
			HttpSession session = request.getSession();  
			String memberLevel =  (String) session.getAttribute("memberLevel"); 
			boolean isAdmin = memberLevel.equals("관리자");
			if(isAdmin) {
				return true; 
			}			
		}
		
		response.sendError(403);	
		return false;
	}
	
}

