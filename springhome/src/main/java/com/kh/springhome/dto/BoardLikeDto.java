package com.kh.springhome.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder	
public class BoardLikeDto {
	private String memberId;
	private int boardNo;
}
