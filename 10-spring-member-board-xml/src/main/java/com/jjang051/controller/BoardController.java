package com.jjang051.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.model.ReplyBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	@Autowired
	ReplyBoardService replyBoardDao;
	
	@Autowired
	ReplyBoardDto replyBoardDto;
	
	@GetMapping("/List.do")
	public String list() {
		
		return "board/list";
	}
	@GetMapping("/Write.do")
	public String write() {
		return "board/write";
	}
	@GetMapping("/ReplyWrite.do")
	public String logout() {
		return "board/reply_write";
	}
	@GetMapping("/Update.do")
	public String update() {
		return "board/update";
	}
	
	@GetMapping("/Delete.do")
	public String delete() {
		return "board/delete";
	}
	@GetMapping("/View.do")
	public String join() {
		return "board/view";
	}
}