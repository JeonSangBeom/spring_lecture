package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Controller annotation을 달면 Spring Container에 담긴다
//spring에는 많은 annotation이 존재한다. 이걸 다외울 필요는 없다

@Controller
public class HomeController {
	
	//business logic이 여기 들어간다
	@RequestMapping(value="/", method=RequestMethod.GET)//"/"으로 들어오면(처음 그냥 프로젝트를 run하면 실행된다) index로 이동하겠단 뜻 get방식으로 세팅
	public String home() { // home이라는 메서드를 실행하면
		System.out.println("index");
		return "index"; // return되면 세팅한 view resolver가 실행되어 접두어 접미어가 붙어서 리턴이 된다 - '/WEB-INF/view/'   index   '.jsp' 이게 들어간다 
	}
	
	@GetMapping("/Sub.do")    // getmapping는 get으로 /sub.do를 요청했을때 sub리턴 
	public String sub() {
		System.out.println("sub");
		return "sub";
	}
	// 이 두개는 지금 get으로 받기 때문에 post형식으로 설정하면 405에러 발생 / 400대는 전부 클라이언트 에러
}