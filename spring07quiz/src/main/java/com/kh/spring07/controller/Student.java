package com.kh.spring07.controller;

public class Student {
	private String name;
	private int score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Student(String name, int score) {
		super();
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	} 
	
}
