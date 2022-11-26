package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	/*
	 * 주소 넘기는 방법
	  <a href="주소">링크<a> - html에서 사용하는 방법 
	  <script>  이용시
	  location.href="주소";
	  history.go(1);   - 백스페이스 역할 
	  history.back();
	  </script>
	 */
	
	
	@GetMapping("/Test01.do")
	public String test01() {
		
		return "redirect:/Sub01.do"; // 페이지를 넘겨 주는 것
	}
	
	@GetMapping("/Sub01.do")
	public String sub01() {
		
		return "sub01";
	}
	
	@GetMapping("/Test02.do")
	public String test02() {
		
		return "forward:/Sub02.do"; // forward는 주소가 바뀌지 않는다
	}
	
	@GetMapping("/Sub02.do")
	public String sub02() {
		
		return "sub02";
	}
	@GetMapping("/Test03.do")
	public String test03() {
		
		return "redirect:/Sub03.do"; // 페이지를 넘겨 주는 것
	}
	
	@GetMapping("/Sub03.do")
	public String sub03() {
		
		return "sub03";
	}
	@GetMapping("/Test04.do")
	public String test04() {
		
		return "forward:/Sub04.do"; // forward는 주소가 바뀌지 않는다
	}
	
	@GetMapping("/Sub04.do")
	public String sub04() {
		
		return "sub04";
	}

}
