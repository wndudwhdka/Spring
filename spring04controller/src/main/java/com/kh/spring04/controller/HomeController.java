package com.kh.spring04.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//컨트롤러 
@Controller
public class HomeController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello!";
	}
	
	@RequestMapping("/Welcom")
	@ResponseBody
	public String welcom() {
		return "Welcom";
	}
	
	@RequestMapping("/dice")
	@ResponseBody
	public String dice() {
		Random r = new Random(); 
		int number = r.nextInt(6)+1;
		return "주사위 : "+number; 
	}
	
}
