package com.jjang051.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjang051.model.MemberDto;
import com.jjang051.model.ReplyBoardDao;
import com.jjang051.model.ReplyBoardDto;
import com.jjang051.model.ReplyBoardService;
import com.jjang051.util.PageWriter;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	@Autowired
	ReplyBoardService replyBoardDao;
	
	@Autowired
	ReplyBoardDto replyBoardDto;
	
	@Autowired
	ReplyBoardDto prevBoardDto;
	
	@Autowired
	ReplyBoardDto nextBoardDto;
	
	
	@GetMapping("/List.do")
	public String list(HttpServletRequest request, Model model) {
		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if(tempClickPage==null) tempClickPage = "1";
		int clickPage = Integer.parseInt(tempClickPage);
		int totalPage =  replyBoardDao.getTotal(search_select, search_word);//replyBoardDao에 얻어둔 total을 가져오기
		int listPerPage = 5; // table의 줄 수
		int pageBlock = 3; // 아래쪽 page number의 한번에 뿌려지는 갯수
		
		int start = (clickPage - 1)*listPerPage+1;
		int end = start+listPerPage - 1;
		
		List<ReplyBoardDto> boardList = // list에서 넘기는 값(item)
	replyBoardDao.getAllList(start, end, search_select, search_word);
		
				String page = 
PageWriter.pageWrite(totalPage, listPerPage, pageBlock, 
		clickPage, "../board/List.do");
		// 페이지 마다 보이기 위해서는 전체 토탈이 필요하고 우선 replyboardao에 오버라이드를 해둔다 gettotal(boardMapper에 id로 만들어둔 것)
	    // 그뒤 listPerPage(리스트 갯수   ) pageBlock(페이지 갯수) clickPage
		
				
		model.addAttribute("boardList",boardList);// list에서 넘기는 값(item) - 전체 boardList를 뿌려주는 것이고
		// list.jsp에서 페이지 값을 넘겨주는 것 
		model.addAttribute("page",page);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("listPerPage",listPerPage);
		model.addAttribute("pageBlock",pageBlock);
		model.addAttribute("clickPage",clickPage);
		
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
	public String view(int no,int num,Model model) {
		replyBoardDto = replyBoardDao.getSelectOne(no);// 답글의 보드 리스트 
		prevBoardDto = replyBoardDao.getPrevSelect(num);//@Autowired 로 자동 주입 받아서 사용
		nextBoardDto = replyBoardDao.getNextSelect(num);

		//모델에 실어서 보내주기
		model.addAttribute("replyBoardDto",replyBoardDto);
		model.addAttribute("prevBoardDto",prevBoardDto);
		model.addAttribute("nextBoardDto",nextBoardDto);
		return "board/view";
	}
}


