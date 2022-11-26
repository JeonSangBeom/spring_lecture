package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("home controller 호출");
		// view resolver  ("/WEB-INF/views"+index+".jsp")
		return "index";
	}
}