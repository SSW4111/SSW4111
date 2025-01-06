package com.kr.spring03.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//스프링의 제 1특징
//- 제어 반전(loC,Inversion Of Control)
//- 직접 생성하지 말고 생성해달라고 부탁해라

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterQuizController {

	@RequestMapping("/cinema")
	public String cinema(int adult, int teen) {
		int adultPrice = 15000;
		int teenPrice = 10000;
		int totalPrice = adult * adultPrice + teen * teenPrice;
		return "총 요금 = " + totalPrice;
	}
	//(추가) 만약에 adult 나 teen 하나만 있다면?
	@RequestMapping("/cinema2")
	public String sinema(
			@RequestParam(required = false, defaultValue = "0") int adult,
			@RequestParam(required = false, defaultValue = "0") int teen) {
		int total = adult * 15000 + teen * 10000;
		return "총 요금은 " + total + "원 입니다.";	
	}
	
	
	@RequestMapping("/coffee")
	public String coffee(
			@RequestParam(required = false, defaultValue = "0")int ame,
			@RequestParam(required = false, defaultValue = "0")int latte) {
		int amePrice = 2500;
		int lattePrice = 3000;
		int totalPrice = ame * amePrice + latte * lattePrice;
		return "총 요금 = " + totalPrice;
	}
	
	
	@RequestMapping("/score")
	public String score(@RequestParam String name,
						@RequestParam int kor,
						@RequestParam int eng,
						@RequestParam int mat ) {
		int total = kor + eng + mat;
		float ave = total /3f;
		
		return "이름 : " + name + "총점수 : " + total +" 평균점수 : " + ave;
	}
	
	@RequestMapping("/subway")
	public String subway(@RequestParam int birth) {
		int free = 0, child = 500, teen = 800, adult = 1400;
		int age = 2025 - birth + 1;
		int price;
		if(age < 8 || age >= 65) {
			price = free;
		}
		else if(age <= 13) {
			price = child; 
		}
		else if(age <= 19) {
			price = teen;
		}
		else {
			price =  adult;
		}
		return "요금 :" + price;
	}
	
	@RequestMapping("/sum")
	public String sum(@RequestParam int begin, @RequestParam int end) {

		int total = 0;
		for(int i=begin; i<=end; i++) {
			total += i;
		}
		return "합계 " + total;
		}
	
	@RequestMapping("/pcroom")
	public String pcroom(@RequestParam String start, @RequestParam String finish) {
		LocalDate today = LocalDate.now();
		LocalTime startTime = LocalTime.parse(start);
		LocalTime finishTime = LocalTime.parse(finish);
		
		LocalDateTime begin, end;
		if(startTime.isBefore(finishTime)) {//시작시각이 종료 시각보다 이전이라면
			begin = LocalDateTime.of(today, startTime);
			end = LocalDateTime.of(today, finishTime);
		}
		else {
			begin = LocalDateTime.of(today.minusDays(1), startTime);
			end = LocalDateTime.of(today, finishTime);
		}
		
		Duration duration = Duration.between(begin,end);
		
		long minutes = duration.toMinutes();
		int price = (int)(minutes * 1000 / 60d);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("이용시간 : ");
		buffer.append(duration.toHoursPart());
		buffer.append("시간 ");
		buffer.append(duration.toMillisPart());
		buffer.append("분");
		buffer.append("<br>");
		buffer.append("이용요금 : ");
		buffer.append(price);
		buffer.append("원");
		return buffer.toString();

		
	}
}
