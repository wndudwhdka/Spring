package com.kh.spring13.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//포켓몬스터와 첨부파일을 연결하는 테이블의 DTO
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PocketmonImageDto {
	private int pocketmonNo, attachmentNo;
}
