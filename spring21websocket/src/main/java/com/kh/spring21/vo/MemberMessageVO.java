package com.kh.spring21.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class MemberMessageVO {
	private String memberId, memberLevel, content;
	private long time;
}