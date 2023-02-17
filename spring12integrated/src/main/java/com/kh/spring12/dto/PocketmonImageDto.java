package com.kh.spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PocketmonImageDto {
	private int pocketmonNo, attachmentNo;
}
