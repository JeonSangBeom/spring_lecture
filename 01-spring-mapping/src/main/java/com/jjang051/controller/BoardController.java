package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	//   /board/List.do   ,  /board/Write.do  , /board/Delete.do  , /board/Update.do
	//    list.jsp (board/list.jsp) / write(board/write.jsp) / delete(board/delete.jsp) / update(board/update.jsp)
	
	@RequestMapping(value="/List.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String list() {
		System.out.println("list");
		return "board/list";
	}
	@RequestMapping(value="/Write.do", method = RequestMethod.GET)
	public String write() {
		System.out.println("write");
		return "board/write";
	}
	@RequestMapping(value="/Delete.do", method = RequestMethod.GET)
	public String delete() {
		System.out.println("delete");
		return "board/delete";
	}
	@RequestMapping(value="/Update.do", method = RequestMethod.GET)
	public String update() {
		System.out.println("update");
		return "board/update";
	}
	
	// spring 4.0이후부터 사용 가능 getMapping을 쓰면 편하게 사용 가능 하지만 requestMapping는 get과 post둘다 받을 수 있는 장점이 있다
	
}