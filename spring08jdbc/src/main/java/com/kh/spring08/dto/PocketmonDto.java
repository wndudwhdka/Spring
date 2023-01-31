package com.kh.spring08.dto;

import org.springframework.stereotype.Repository;

@Repository
public class PocketmonDto {

	private int no;
	private String name;
	private String type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PocketmonDto [no=" + no + ", name=" + name + ", type=" + type + "]";
	}
	public PocketmonDto() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
