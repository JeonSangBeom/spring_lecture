package com.jjang051.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.jjang051.model.DataDto;

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
	
	
	//request에 실어 보내기
	@GetMapping("/Test01.do")
	public String test01(HttpServletRequest request) { // 데이터 실기
		request.setAttribute("data01", "나는 data01"); // 이 데이터가 실려서 넘어간다
		return "forward:/Sub01.do"; 
	}
	
	@GetMapping("/Sub01.do")
	public String sub01(HttpServletRequest request) {
		String data01 = (String)request.getAttribute("data01");
		System.out.println(data01); // html외에 데이터넘어올때 사용
		return "sub01";
	}
	
	
	//model에 담기 - 가장 많이 사용
	@GetMapping("/Test02.do")
	public String test02(Model model) { // 데이터 실기
		model.addAttribute("data02", "나는 data02"); // 이 데이터가 실려서 넘어간다
		return "forward:/Sub02.do"; 
	}
	
	@GetMapping("/Sub02.do")
	public String sub02(HttpServletRequest request) {
		String data02 = (String)request.getAttribute("data02"); // 모델에 실어 보내도 request에 담긴다
		System.out.println(data02); // html외에 데이터넘어올때 사용
		return "sub02";
	}
	
	
	//modelandview
	@GetMapping("/Test03.do")
	public ModelAndView test03(ModelAndView mav) { // 데이터 실기
		mav.addObject("data03", "나는 data03"); // 이 데이터가 실려서 넘어간다
		mav.setViewName("forward:/Sub03.do");
		return mav; 
	}
	
	@GetMapping("/Sub03.do")
	public String sub03(HttpServletRequest request) {
		String data03 = (String)request.getAttribute("data03"); // 모델앤뷰 실어 보내도 request에 담긴다
		System.out.println(data03); // html외에 데이터넘어올때 사용
		return "sub03";
	}
	
	// 모델 - dataDto이용
	@GetMapping("/Test04.do")
	public String test04(Model model) { // 데이터 실기
		DataDto dataDto = new DataDto();
		dataDto.setData01("dataDto data01"); // 내용 보내기
		dataDto.setData02("dataDto data02");
		model.addAttribute("dataDto", dataDto); // dataDto로 실은 것 
		return "forward:/Sub04.do"; 
	}
	
	@GetMapping("/Sub04.do")
	public String sub04(HttpServletRequest request) {
		DataDto dataDto = (DataDto)request.getAttribute("dataDto"); // 실은 dataDto를  dataDto에 담기
		System.out.println(dataDto.toString()); // html외에 데이터넘어올때 사용
		return "sub04";
	}
	
	
	@GetMapping("/Test05.do")
	public String test05(@ModelAttribute("dataDto") DataDto dataDto) { // 데이터 실기 @ModelAttribute("dataDto")- 생략 가능
		dataDto.setData01("modelAttribute dataDto data01"); // 내용 보내기
		dataDto.setData02("modelAttribute dataDto data02");
		return "forward:/Sub05.do"; 
	}
	
	@GetMapping("/Sub05.do")
	public String sub05(HttpServletRequest request) {
	//public String sub05(DataDto dataDto) {
		DataDto dataDto = (DataDto)request.getAttribute("dataDto"); // 실은 dataDto를  dataDto에 담기
		System.out.println(dataDto.toString()); // html외에 데이터넘어올때 사용
		return "sub05";
	}

}
