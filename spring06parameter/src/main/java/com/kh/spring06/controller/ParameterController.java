package com.kh.spring06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
public class ParameterController {

	@RequestMapping("/age")
	@ResponseBody
	public String age(@RequestParam(required = false) String name, 
					  @RequestParam int birth) {
		System.out.println("name = " + name); 
		System.out.println("age = " + birth);
		int result = 2023-birth +1; 
		return name+"님의"+" 나이는 "+	result+"입니다"; 
	}
}
