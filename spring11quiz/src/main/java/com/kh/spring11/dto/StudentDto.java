package com.kh.spring11.dto;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDto {
	private int no;
	private String name;
	private int korean, english, math;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public StudentDto() {
		super();
	}
	//합계를 확인하기 위해 추가한 가상의 Getter
	public int getTotal() {
		return korean + english + math;
	}
	//평균을 확인하기 위해 추가한 가상의 Getter
	public double getAverage() {
		return getTotal() / 3d;
	}
	@Override
	public String toString() {
		return "StudentDto [no=" + no + ", name=" + name + ", korean=" + korean + ", english=" + english + ", math="
				+ math + ", getTotal()=" + getTotal() + ", getAverage()=" + getAverage() + "]";
	}
}
