package com.kh.spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PocketmonWithImageDto {
	private int no;
	private String name;
	private String type;
	private Integer attachmentNo; // null이 나오도록 하기 위해서   
}