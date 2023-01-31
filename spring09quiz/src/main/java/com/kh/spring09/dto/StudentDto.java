package com.kh.spring09.dto;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDto {
	private int no,korean,english,math;
	private String name;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StudentDto [no=" + no + ", korean=" + korean + ", english=" + english + ", math=" + math + ", name="
				+ name + "]";
	} 
	
}
