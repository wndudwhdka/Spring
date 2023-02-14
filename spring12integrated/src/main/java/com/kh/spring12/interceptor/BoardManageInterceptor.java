package com.kh.spring12.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring12.dao.BoardDao;
import com.kh.spring12.dto.BoardDto;

@Service
public class BoardManageInterceptor implements HandlerInterceptor{

	
	@Autowired
	private BoardDao boardDao; 
	
//	@Autowired
//	private 
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, // 사용자가 보낸 정보
			HttpServletResponse response, // 사용자에게 보낼 정보 
			Object handler)
			throws Exception {
		// 작성자 본인이라는 것은 게시글의 작성자와 현재 세션의 회원아이디가 같음
		//- 게시글 정볼르 불러오려면 게시글 번호와 BoardDao가 필요하다
		//- 게시글 번호는 파라미터(Parameter) 형태로 전송
		
		
		// 게시글 작성자 확인
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardDto boardDto = boardDao.selectOne(boardNo);
		// database의 제약조건을 보면 해당 값이 null일지를 예측할 수 있다. 
		String writerId = boardDto.getBoardWriter();
		
		// 현재 로그인 회원 확인
		HttpSession session = request.getSession(); 
		String memberId = (String)session.getAttribute("memberId");
		
		boolean isOwner = memberId.equals(writerId);
				
		// 현재 로그인 회원의 등급 확인
		String memberLevel = (String)session.getAttribute("memberLevel");
		
		boolean isAdmin = memberLevel.equals("관리자"); 
		
		if(isAdmin)	//	관리자라면
		{
//			System.out.println(request.getRequestURI());
			if(request.getRequestURI().equals("/board/delete")) {
				return true;				
			}
 
		}
		if(isOwner) // 작성자 본인이면 
		{
			return true;
		}
		response.sendError(403);
		return false; 
	}
	
	
	
	
}
