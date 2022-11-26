package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sub") // 중복되는 주소를 위로 올릴 수 있다 / 링크에 나오는 주소
public class SubController02 {
	
	@RequestMapping(value="/Sub03.do",method=RequestMethod.GET)
	public String sub01() {
		System.out.println("sub03 호출");
		return "sub02/sub03"; // jsp를 호출하는 것으로 이곳은 지우면 안된다
	}
	
	@RequestMapping(value="/Sub04.do",method=RequestMethod.GET)
	public String sub02() {
		System.out.println("sub04호출");
		return "sub02/sub04";
	}
	
	
}