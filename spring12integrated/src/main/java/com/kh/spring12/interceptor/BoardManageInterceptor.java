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
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, //사용자가 보낸 정보
			HttpServletResponse response, //사용자에게 보낼 정보
			Object handler)
			throws Exception {
		//작성자 본인이라는 것은 게시글의 작성자와 현재 세션의 회원아이디가 같음을 의미
		//- 게시글 정보를 불러오려면 게시글 번호와 BoardDao가 필요
		//- 게시글 번호는 파라미터(Parameter) 형태로 전송
		//- request.getParameter("이름") 작성 시 반환형이 String
		
		//게시글 작성자 확인 코드
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardDto boardDto = boardDao.selectOne(boardNo);
		String writerId = boardDto.getBoardWriter();
		
		//현재 로그인 회원 확인 코드
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		boolean isOwner = memberId.equals(writerId);
		
		//현재 로그인 회원의 등급 확인 코드
		String memberLevel = (String)session.getAttribute("memberLevel");
		
//		boolean isAdmin = memberLevel != null && memberLevel.equals("관리자");
		boolean isAdmin = memberLevel.equals("관리자");
		
//		if(memberId != null && writerId != null && memberId.equals(writerId)) {
//		if(memberId.equals(writerId)) {//작성자 본인이라면
		if(isAdmin) {//관리자
			//삭제 페이지로 이동한다면
			if(request.getRequestURI().equals("/board/delete")) {
				return true;
			}
		}
		if(isOwner) {//작성자 본인
			return true;
		}
		
		//조건에 해당하지 않는 경우는 모두 차단
		response.sendError(403);
		return false;
	}
}
