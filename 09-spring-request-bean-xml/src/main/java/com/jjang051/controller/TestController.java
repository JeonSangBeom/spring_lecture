package com.jjang051.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jjang051.model.DataDto;
import com.jjang051.model.DataDto02;
import com.jjang051.model.MemberDto;

@Controller
public class TestController {

	// 자동 주입...
	@Autowired
	DataDto dataDto;

	@Autowired
	DataDto02 dataDto02;
	
	@Autowired
	MemberDto memberDto;
	
	
	@GetMapping("/Test01.do")
	public String test01() {
		dataDto.setData01("나는 자동 주입된 dataDto의 속성중 하나인 data01");
		dataDto.setData02("나는 자동 주입된 dataDto의 속성중 하나인 data02");
		dataDto02.setData0201("나는 직접 생성된 dataDto02의 속성중 하나인 data0201");
		dataDto02.setData0202("나는 직접 생성된 dataDto02의 속성중 하나인 data0202");
		
		return "forward:/Sub01.do";
	}

	@GetMapping("/Sub01.do")
	public String sub01(Model model) {
		
		model.addAttribute("dataDto", dataDto);
		model.addAttribute("dataDto02", dataDto02);
		
		return "sub01";
	}

	@GetMapping("/Test02.do")
	public String test02() {
		memberDto.setNo(1);
		memberDto.setName("홍길동");
		memberDto.setAge(1);
		return "forward:Sub02.do";
	}
	
	@GetMapping("/Sub02.do")
	public String sub02(Model model) {
		model.addAttribute("dataDto", dataDto);
		return "sub02";
	}
}










