package com.kh.spring12.dto;

import lombok.Data;

@Data
public class StudentDto {
	private int no;
	private String name;
	private int korean, english, math;
	//합계를 확인하기 위해 추가한 가상의 Getter
	public int getTotal() {
		return korean + english + math;
	}
	//평균을 확인하기 위해 추가한 가상의 Getter
	public double getAverage() {
		return getTotal() / 3d;
	}
}
