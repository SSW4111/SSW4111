package com.kh.spring01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController{
	
	@RequestMapping("/")
	public String hello() {
		return "Hello Spring Boot!";
	}
}


