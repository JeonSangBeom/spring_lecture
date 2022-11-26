package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubController {
	
	@RequestMapping(value="/sub/Sub01.do",method=RequestMethod.GET)
	public String sub01() {
		System.out.println("sub01 호출");
		return "sub/sub01";
	}
	
	@RequestMapping(value="/sub/Sub02.do",method=RequestMethod.GET)
	public String sub02() {
		System.out.println("sub02 호출");
		return "sub/sub02";
	}
	
	
}