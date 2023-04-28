package com.kh.spring19.dto;

import lombok.Data;

@Data
public class StudentDto {
	private int no;
	private String name;
	private int korean, english, math;
}
