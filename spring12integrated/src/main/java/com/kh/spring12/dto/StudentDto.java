package com.kh.spring12.dto;

import lombok.Data;

@Data
public class StudentDto {
	private int no;
	private String name;
	private int korean, english, math;
	public double getAverage(){
		return (this.korean+this.english+this.math)/3.0;
	}
	public int getTotal() {
		return this.korean+this.english+this.math;
	}
}
