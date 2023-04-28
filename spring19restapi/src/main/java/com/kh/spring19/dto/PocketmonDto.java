package com.kh.spring19.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class PocketmonDto {
	
	//@Parameter는 @ParameterObject에 대한 속성별 정보 설정용이다
	@Parameter(description = "포켓몬 번호", example = "1", required = true)
	private int no;
	
	@Parameter(description = "포켓몬 이름", example = "이상해씨", required = true)
	private String name;
	
	@Parameter(description = "포켓몬 속성", example = "풀", required = true)
	private String type;
	
}



