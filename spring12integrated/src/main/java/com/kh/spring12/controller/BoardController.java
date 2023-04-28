package com.kh.spring12.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring12.dao.BoardDao;
import com.kh.spring12.dto.BoardDto;
import com.kh.spring12.service.BoardService;
import com.kh.spring12.vo.PaginationVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private BoardService boardService;
	
//	목록 및 검색
//	@GetMapping("/list")
//	public String list(Model model, 
//			@RequestParam(required = false, defaultValue = "boardTitle") String column, 
//			@RequestParam(required = false, defaultValue = "") String keyword) {
//		if(keyword.equals("")) {//키워드가 없다면 -> 목록
//			model.addAttribute("list", boardDao.selectList());
//		}
//		else {//키워드가 있다면 -> 검색
//			model.addAttribute("column", column);
//			model.addAttribute("keyword", keyword);
//			model.addAttribute("list", boardDao.selectList(column, keyword));
//		}
//		//검색 여부와 관계 없이 공지사항을 3개 조회해서 Model에 첨부
//		model.addAttribute("noticeList", boardDao.selectNoticeList(1, 3));
//		return "/WEB-INF/views/board/list.jsp";
//	}
	
//	(+추가) ModelAttribute는 자동 수신 외에 기능이 하나 더 있다.
//	-----> Model에 자동으로 추가됨(이름을 설정해야 함)
	@GetMapping("/list")
	public String list(
			@ModelAttribute("vo") PaginationVO vo, 
			Model model) {
		//vo에 딱 한 가지 없는 데이터가 게시글 개수(목록/검색이 다름)
		int totalCount = boardDao.selectCount(vo);
		vo.setCount(totalCount);
		
		//공지사항
		model.addAttribute("noticeList", boardDao.selectNoticeList(1, 3));
		
		//게시글
		List<BoardDto> list = boardDao.selectList(vo);
		model.addAttribute("list", list);
		
		return "/WEB-INF/views/board/list2.jsp";
	}
	
//	조회수 중복 방지 시나리오
//	1. 작성자 본인은 조회수 증가를 하지 않는다
//	2. 한 번 이상 본 글은 조회수 증가를 하지 않는다
//		(1) 세션에 현재 사용자가 읽은 글 번호를 저장해야 한다
//		(2) 새로운 글을 읽으려 할 때 현재 읽는 글 번호가 읽은 이력이 있는지 조회
//		(3) 읽은 적이 있으면 조회수 증가를 하지 않고 없으면 추가 후 조회수 증가
	
	@GetMapping("/detail")
	public String detail(@RequestParam int boardNo, 
						Model model, HttpSession session) {
		//사용자가 작성자인지 판정 후 JSP로 전달
		BoardDto boardDto = boardDao.selectOne(boardNo);
		String memberId = (String) session.getAttribute("memberId");
		
		boolean owner = boardDto.getBoardWriter() != null 
				&& boardDto.getBoardWriter().equals(memberId);
		model.addAttribute("owner", owner);
		
		//사용자가 관리자인지 판정 후 JSP로 전달
		String memberLevel = (String) session.getAttribute("memberLevel");
		boolean admin = memberLevel != null && memberLevel.equals("관리자");
		model.addAttribute("admin", admin);
		
		//조회수 증가
		if(!owner) {//내가 작성한 글이 아니라면(시나리오 1번)
			
			//시나리오 2번 진행
			Set<Integer> memory = (Set<Integer>) session.getAttribute("memory");
			if(memory == null) {
				memory = new HashSet<>();
			}
			
			if(!memory.contains(boardNo)) {//읽은 적이 없는가(기억에 없는가)
				boardDao.updateReadcount(boardNo);
				boardDto.setBoardRead(boardDto.getBoardRead()+1);//DTO 조회수 1증가
				memory.add(boardNo);//저장소에 추가(기억에 추가)
			}
			//System.out.println("memory = " + memory);
			session.setAttribute("memory", memory);//저장소 갱신
			
		}
		model.addAttribute("boardDto", boardDto);
		return "/WEB-INF/views/board/detail.jsp";
	}
	
	//경로 변수 방식의 상세조회
	//- 매핑 주소에 중괄호를 적고 변수명을 작성하면 스프링에서 수신해준다
	//- 매개변수에 @PathVariable 형태로 주소에 작성한 변수명을 선언한다
	//장점
	//- 주소의 가독성 증가
	//- 전송방식과 무관하게 사용이 가능
	//- 정규표현식 검사가 가능
	//단점
	//- 엔드포인트(endpoint)가 달라져서 상대경로를 쓰기 불편함
	//- 보내는 데이터 양이 많아질 수록 가독성이 오히려 안좋아진다
	//- 경로 변수 방식을 지원하지 않는 라이브러리들이 있음(수작업)
	@GetMapping("/detail/{boardNo}")
	public String detail2(@PathVariable int boardNo, Model model) {
		model.addAttribute("boardDto", boardDao.selectOne(boardNo));
		return "/WEB-INF/views/board/detail.jsp";
	}
	
	@GetMapping("/write")
	public String write(
			@RequestParam(required = false) Integer boardParent,
			Model model) {
		model.addAttribute("boardParent", boardParent);
		return "/WEB-INF/views/board/write.jsp";
	}
	
//	@PostMapping("/write")
//	public String write(
//			@ModelAttribute BoardDto boardDto,//3개(말머리,제목,내용)
//			HttpSession session, RedirectAttributes attr
//			) {
//		//번호와 회원아이디를 추출
//		int boardNo = boardDao.sequence();
//		String memberId = (String)session.getAttribute("memberId");
//		
//		//작성한 게시글 정보에 첨부
//		boardDto.setBoardNo(boardNo);
//		boardDto.setBoardWriter(memberId);
//		
//		//게시글을 등록
//		boardDao.insert(boardDto);
//		
//		//상세 페이지로 이동
//		attr.addAttribute("boardNo", boardNo);
//		return "redirect:detail";
//	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDto boardDto,
			@RequestParam(required=false) List<Integer> attachmentNo,
			HttpSession session, RedirectAttributes attr) {
		//컨트롤러에서만 가능한 작업은 컨트롤러에서 처리
		//- 사용자의 요청을 처리하는 것
		//- 세션 사용
		//- 리다이렉트 관련 처리
		//- 그 외 사용자 요청 처리 관련 도구 사용
		String memberId = (String)session.getAttribute("memberId");
		boardDto.setBoardWriter(memberId);
		
		//나머지 일반 프로그래밍 코드는 서비스를 호출하여 처리
		int boardNo = boardService.write(boardDto, attachmentNo);
		
		//상세 페이지로 redirect
		attr.addAttribute("boardNo", boardNo);
		return "redirect:detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int boardNo) {
		boardDao.delete(boardNo);
//		return "redirect:list";//상대경로
		return "redirect:/board/list";//절대경로
	}
	
	@GetMapping("/delete/{boardNo}")
	public String delete2(@PathVariable int boardNo) {
		boardDao.delete(boardNo);
//		return "redirect:../list";//상대경로
		return "redirect:/board/list";//절대경로
	}
	
//	할일 : 
//	- 준비 : 글번호
//	- 처리 : 글정보 조회
//	- 결과 : 화면에 조회한 데이터 전달
	@GetMapping("/edit")
	public String edit(@RequestParam int boardNo, Model model) {
		model.addAttribute("boardDto", boardDao.selectOne(boardNo));
		return "/WEB-INF/views/board/edit.jsp";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute BoardDto boardDto, 
											RedirectAttributes attr) {
		boardDao.update(boardDto);
		attr.addAttribute("boardNo", boardDto.getBoardNo());
		return "redirect:detail";
	}
	
	//관리자를 위한 전체 삭제 기능
	// - boardNo=1&boardNo=2&boardNo=3 형태로 전송됨
	// - List<Integer> 형태로 수신하거나 int[] 형태로 수신해야함
	// - @RequestParam에 value를 적으면 수신이름을 별도로 지정할 수 있음
	@PostMapping("/deleteAll")
	public String deleteAll(
			@RequestParam(value="boardNo") List<Integer> list) {
		for(int boardNo : list) {
			boardDao.delete(boardNo);
		}
		return "redirect:list";
	}
	
}






