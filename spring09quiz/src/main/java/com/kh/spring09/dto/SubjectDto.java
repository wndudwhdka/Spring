package com.kh.spring09.dto;

import org.springframework.stereotype.Repository;


@Repository
public class SubjectDto {
	private int no,period,price,type; 
	private String name;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SubjectDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SubjectDto [no=" + no + ", period=" + period + ", price=" + price + ", type=" + type + ", name=" + name
				+ "]";
	} 
	
}
