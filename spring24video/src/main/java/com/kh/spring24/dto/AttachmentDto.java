package com.kh.spring24.dto;

import com.kh.spring24.dto.AttachmentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AttachmentDto {
	private int attachmentNo;
	private String attachmentName;
	private String attachmentType;
	private long attachmentSize;
}
