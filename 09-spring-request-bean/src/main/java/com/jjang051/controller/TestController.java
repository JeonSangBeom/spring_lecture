package com.jjang051.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jjang051.model.BoardDto;
import com.jjang051.model.DataDto;
import com.jjang051.model.DataDto02;
import com.jjang051.model.MemberDto;

@Controller
public class TestController {

	// Autowired - 자동 주입 
	@Autowired
	DataDto dataDto;
	
	@Autowired
	MemberDto memberDto;
	
	@Autowired
	DataDto02 dataDto02;
	
	@Autowired
	BoardDto boardDto;
	
	@GetMapping("/Test01.do")
	public String test01() {
		dataDto.setData01("나는 자동 주입된 dataDto의 속성중 하나인 data01");
		dataDto.setData02("나는 자동 주입된 dataDto의 속성중 하나인 data02");
		
		dataDto02.setData03("나는 직접 등록한 dataDto02의 속성중 하나인 data03");
		dataDto02.setData04("나는 직접 등록한 dataDto02의 속성중 하나인 data04");
		
		
		boardDto.setNo(10);
		boardDto.setSubject("제목을 씁니다.");
		boardDto.setHit(100);
		boardDto.setRegDate("2022-02-23");
		
		return "forward:/Sub01.do";
	}

	@GetMapping("/Sub01.do")
	public String sub01(Model model) {
		
		model.addAttribute("dataDto", dataDto);
		model.addAttribute("dataDto02", dataDto02);
		model.addAttribute("boardDto", boardDto);
		
		return "sub01";
	}

	@GetMapping("/Test02.do")
	public String test02() {
		memberDto.setNo(1); // auto이기 때문에 new memberdto를 할 필요가 없다
		memberDto.setName("홍길동");
		memberDto.setAge(10);
		return "forward:Sub02.do";
	}
	
	@GetMapping("/Sub02.do")
	public String sub02(Model model) {
		model.addAttribute("memberDto", memberDto);
		return "sub02";
	}
}



