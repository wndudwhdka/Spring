package com.kh.spring12.dto;

import lombok.Data;

@Data
public class MemberStatDto {
	private String member_level;
	private int cnt,pointSum,pointAvg,pointMax,pointMin;
}
