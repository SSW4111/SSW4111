package com.kh.spring02;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


	
@RestController //특정 주소에 대해서 상호작용(요청/응답)할 수 있는 도구
public class HelloController{
	
	@RequestMapping("/hello") //원하는 주소를 지정하는 도구
	public String hello() {
		return "Hello Spring Boot!";
	}
	
	@RequestMapping("/goodbye")
	public String goodbye() {
		return"Goodbye!";
	}
	//(Q)`/dice`라는 주소로 들어오면 랜덤 주사위 값 1개 출력
	
	@RequestMapping("/dice")
	public String dice() {
		Random r = new Random();
		int dice = r.nextInt(6)+1;
		return "주사위 값 = " + dice;
	}
	
	//(Q)`/lotto`라는 주소로 들어오면 랜덤 로또 번호 6개 출력
	@RequestMapping("/lotto")
	public String lotto() {
		Random r = new Random();
		int number = r.nextInt(45)+1;
		Set<Integer> numbers =  new TreeSet<>();
		while(numbers.size()<6){
			numbers.add(r.nextInt(45)+1);
		}
		return "로또번호 : "  + numbers;
	}
	
	//(Q) Scanner를 쓸 수 없다면 사용자가 전송하는 데이터를 어떻게 받을까?
	// - 주소가 http://localhost:8080/plus?left=10&right=20일 경우
	@RequestMapping("/plus")
	public String plus(int left, int right) {
		int total = left + right;
		return "덧셈 결과 : "+ total;
	}
	
}


