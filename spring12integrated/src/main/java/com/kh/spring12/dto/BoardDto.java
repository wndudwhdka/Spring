package com.kh.spring12.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDto {
	private int boardNo;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private Date boardTime;
	private String boardHead;
	private int boardRead;
	private int boardLike;
	private int boardReply;
}
