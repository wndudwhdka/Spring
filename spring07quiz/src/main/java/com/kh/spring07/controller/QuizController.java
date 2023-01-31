package com.kh.spring07.controller;

import java.text.DecimalFormat;
import java.text.Format;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuizController {
	@RequestMapping("/theater")
	@ResponseBody
	public String theater(@RequestParam(required=false,defaultValue = "0")int adult, @RequestParam(required=false,defaultValue = "0")int teen)
	{
		Format f = new DecimalFormat("###,###");
		return "어른 "+adult+"명, 청소년 "+teen+"명의 티켓 금액은 "+f.format(adult*16000+teen*10000)+"원 입니다."; 
	}
	
	@RequestMapping("/china")
	@ResponseBody
	public String china(@RequestParam(required=false,defaultValue = "0") int jjajang, @RequestParam(required=false,defaultValue = "0")int champon)
	{
		Format f = new DecimalFormat("###,##0");
		return "짜장 "+jjajang+"그릇, 짬뽕 "+champon+"그릇의 가격은 "+f.format(jjajang*6000+champon*7500)+"원 입니다.";
	}
	
	@RequestMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam double height, @RequestParam double weight)
	{
		Format f = new DecimalFormat("00.00");
		return "키가 "+height+"cm, 몸무게가 "+weight+"kg인 사람의 bmi는 "+f.format(weight/((height/100.0)*(height/100.0)))+" 입니다.";
		
	}
	
	@RequestMapping("/subway")
	@ResponseBody
	public String subway(@RequestParam int birth)
	{
		Format f = new DecimalFormat("#,000");
		int price = 0;
		int age = 2023 - birth +1; 
		if(age > 64)
		{
		}
		else if(age > 19)
		{
			price = 1250;
		}
		else if(age > 13)
		{
			price = 720;
		}
		else if(age > 7)
		{
			price = 450;
		}
		else 
		{
			
		}
		if(price==0) return "당신의 나이는 "+age+"이며 요금은 무료입니다!";
		else return "당신의 나이는 "+age+"이며 요금은 "+f.format(price)+"원 입니다!";
		
	}
	
	@RequestMapping("/pcroom")
	@ResponseBody
	public String pcroom(@RequestParam int start , @RequestParam int finish )
	{
		Format f = new DecimalFormat("#,###,###.###"); 
		int usedTime = finish-start;
		double pricePerMinute = 1300/60.0; 
		return "총 이용 시간은 "+usedTime+"분 이며 금액은 "+usedTime*pricePerMinute+"원 입니다.";
		
	}
	
	@RequestMapping("/sum")
	@ResponseBody
	public String sum(@RequestParam int start,@RequestParam int end)
	{
		int sum=0; 
		for(int i=start;i<=end;i++)
		{
			sum+=i;
		}
		return "정답은 "+sum+"입니다.";
	}
	
	
	@RequestMapping("/score")
	@ResponseBody
	public String score(@RequestParam int korean,@RequestParam int english, @RequestParam int math)
	{
		if((korean+english+math)>180&&korean>=40&english>=40&&math>=40)
		{
			return "평균 "+(korean+english+math)/3+", 국어 "+korean+"점, 영어 "+english+"점, 수학 "+math+"점으로 합격입니다.";		
		}
		else return "평균 "+(korean+english+math)/3+", 국어 "+korean+"점, 영어 "+english+"점, 수학 "+math+"점으로 불합격입니다.";
	}
	
	@RequestMapping("/leap")
	@ResponseBody
	public String leap(@RequestParam int year)
	{
		if(year/4==0&&!(year%100==0)||year%400==0) return "해당 연도 "+year+"은 윤년입니다.";
		else return "해당 연도 "+year+"은 윤년이 아닙니다.";	
	}
	
	@RequestMapping("/student")
	@ResponseBody
	public String student(@ModelAttribute Student student)
	{
		return "받은 데이터 : " + student.toString();
	}
	
}
