package com.kh.spring24.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeTagDto {
	// 통합 게시물 번호 
	private Long freeTagNo; 
	// 태그 
	private String freeTagName;
}
