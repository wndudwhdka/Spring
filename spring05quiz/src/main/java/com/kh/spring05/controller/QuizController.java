package com.kh.spring05.controller;


import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuizController {

	Random r = new Random();
	@RequestMapping("/lotto")
	@ResponseBody
	public String lotto() {
		
		List<Integer> list = new ArrayList<Integer>();
		int temp; 
		while(list.size()!=6)
		{
			temp = r.nextInt(45)+1; 
			if(!list.contains(temp))
			{
				list.add(temp); 
			}
		}
		return list.toString();
	}
	
	@RequestMapping("/otp")
	@ResponseBody
	public String otp(){
		int number = r.nextInt(1000000);
		Format f = new DecimalFormat("000000");
		return "otp = " +f.format(number); 
		
	}
	
}
