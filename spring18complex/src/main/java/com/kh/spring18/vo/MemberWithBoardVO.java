package com.kh.spring18.vo;

import java.util.List;

import com.kh.spring18.dto.BoardDto;
import com.kh.spring18.dto.MemberDto;

import lombok.Data;

//회원 정보와 그 회원이 작성한 게시글 목록을 가지는 클래스
@Data
public class MemberWithBoardVO {
	private MemberDto memberDto;
	private List<BoardDto> boardList;
}
