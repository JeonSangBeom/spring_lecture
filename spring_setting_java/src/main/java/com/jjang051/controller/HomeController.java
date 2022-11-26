package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	
	@RequestMapping(value="/", method=RequestMethod.GET) // 메서드는 중요하지 않다 이 곳이 중요 value="/" 이 위치로 
	public String home() { // 리턴하면 자동으로 jsp가 사라진다
		System.out.println("home controller 호출");
		return "index";
	}
}