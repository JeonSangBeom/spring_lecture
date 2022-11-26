package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/List.do")
	//@RequestMapping(value="/List.do", method = {RequestMethod.GET,RequestMethod.POST})의 장점 -> 둘다 받을 수 있다.
	public String list() {
		return "member/list";
	}
	@GetMapping("/Write.do")
	public String write() {
		return "member/list";
	}
	@GetMapping("/Delete.do")
	public String delete() {
		return "member/delete";
	}
	@GetMapping("/Update.do")
	public String update() {
		return "member/update";
	}
}