package com.kh.spring12.dto;





public class SubjectDto {
	private int no;
	private String name;
	private int period;
	private int price;
	private String type;
	
	@Override
	public String toString() {
		return "SubjectDto [no=" + no + ", name=" + name + ", period=" + period + ", price=" + price + ", type=" + type
				+ "]";
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SubjectDto() {
		super();
	}
}