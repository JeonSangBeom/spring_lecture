package com.jjang051.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.model.MemberService;
import com.jjang051.util.PageWriter;
import com.jjang051.util.ScriptWriter;

@Controller
@RequestMapping("/member") // 한번에 빼서 사용 가능
public class MemberController {
	
	@Autowired
	MemberDto memberDto;
	
	@Autowired
	MemberService memberDao;  //Interface를 구현한 것은 어떤것도 상관없이 쓸 수 있다.
	
	
	@GetMapping("/List.do")
	public String list(HttpServletRequest request,Model model) {
		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if(tempClickPage==null) tempClickPage = "1";
		int clickPage = Integer.parseInt(tempClickPage);
		int totalPage = 20;
		
		List<MemberDto> memberList = memberDao.getAllMemberList(1, 5, search_select, search_word); 
		// MemberDto의 List을 리턴 받는 memberList는 memberDao.getAllMemberList를 받아온다 -> 그 뒤 dao에 가서 작업 해주기
		
		String page = PageWriter.pageWrite(10, 5, 3, clickPage, "member/List.do");
		
		model.addAttribute("memberList",memberList);
		model.addAttribute("page",page);
		model.addAttribute("totalPage",10);
		
		return "member/list";  // jsp 페이지 설정
	}
	
	// responsebody - 
	@GetMapping("/JsonList.do")
	@ResponseBody
	public Map<String, Object> jsonList(HttpServletRequest request,Model model) {
		String tempClickPage = request.getParameter("clickPage");
		String search_select = request.getParameter("search_select");
		String search_word = request.getParameter("search_word");
		if(tempClickPage==null) tempClickPage = "1";
		int clickPage = Integer.parseInt(tempClickPage);
		int totalPage = 20;
		
		List<MemberDto> memberList = memberDao.getAllMemberList(1, 5, search_select, search_word);
		
		String page = PageWriter.pageWrite(10, 5, 3, clickPage, "member/List.do");
		
		model.addAttribute("memberList",memberList);
		model.addAttribute("page",page);
		model.addAttribute("totalPage",10);
		
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("jsonMemberList",memberList);
		
		return jsonMap;  // jsp 페이지 설정
	}
	
	
	@GetMapping("/Login.do")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/LoginProcess.do")
	public void loginProcess(MemberDto memberDto,HttpServletResponse response, HttpSession session) throws Exception {
		memberDto = memberDao.getLoggedMember(memberDto);
		if(memberDto!=null) {
			session.setAttribute("loggedMember", memberDto);
			session.setAttribute("loggedId", memberDto.getId());
			session.setAttribute("loggedName", memberDto.getName());
			// header에 있는 loggedMember Id Name
			ScriptWriter.alertAndNext(response, "로그인 되었습니다.", "../");
		} else {
			ScriptWriter.alertAndBack(response, "아이디 패스워드 확인해 주세요.");
		}
	}
	
	
	@GetMapping("/LogOut.do")
	public String logout() {
		return "member/logout";
	}
	@GetMapping("/Update.do")
	public String update() {
		return "member/update";
	}
	
	@GetMapping("/Delete.do")
	public String delete() {
		
		return "member/delete";
	}
	
	@PostMapping("/DeleteProcess.do")
	public void deleteProcess(MemberDto memberDto,HttpServletResponse response) throws Exception {
		int result = memberDao.deleteMember(memberDto);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원탈퇴 되었습니다.", "../");
		} else {
			ScriptWriter.alertAndBack(response, "아이디 패스워드 확인해 주세요.");
		}
	}
	
	
	
	@GetMapping("/Join.do")// 페이지 보여주는 것
	public String join(HttpServletRequest request) {
		return "member/join";
	}
	
	@PostMapping("/JoinProcess.do")
	public void joinProcess(MemberDto memberDto,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//response를 받아야 쓸 수 있다
		memberDto.setAddress(memberDto.getAddress01()+" "+memberDto.getAddress02());
		memberDto.setPhone(memberDto.getPhoneFirst()+"-"+memberDto.getPhoneMiddle()+"-"+memberDto.getPhoneLast());
		int result = memberDao.insertMember(memberDto); //insertMember를 dto로 던져 주기
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원가입 되었습니다. 로그인 해주세요.", "../member/Login.do");
		} else {
			ScriptWriter.alertAndBack(response, "시스템 오류입니다. 다시 시도해 주세요.");
		}
		//return null;
	}
	
	@GetMapping("/Info.do")
	public String info(String user_id,Model model) {// model addattribute의 존재로 아래를 사용하지 안ㄶ아도 된다
		//String user_id = request.getParameter("user_id");
		//System.out.println("user_id==="+user_id);
		memberDto = memberDao.getSelectOne(user_id);
		model.addAttribute("memberDto",memberDto);
		return "member/info";
	}
	
	// url링크로 넘길거라 void로 이용
	@PostMapping("/UpdateProcess.do")
	public void updateProcess(MemberDto memberDto,HttpServletResponse response) throws Exception {// dto와 response(이게 있어야 아래 if에서 글을 쓸수 있다)를 받아오기
		System.out.println("memberDto==="+memberDto);
		memberDto.setPhone(memberDto.getPhoneFirst()+"-"+memberDto.getPhoneMiddle()+"-"+memberDto.getPhoneLast());
		memberDto.setAddress(memberDto.getAddress01()+" "+memberDto.getAddress02());
		int result = memberDao.updateMember(memberDto);
		if(result>0) {
			ScriptWriter.alertAndNext(response, "회원정보가 수정되었습니다.", "../member/List.do");
		} else {
			ScriptWriter.alertAndBack(response, "시스템 오류 입니다. 잠시 후 다시 시도해주세요.");
		}
	} 
	
	//ResponseBody - Json Return   jackson을 pom.xml에 추가해야 한다.
	@ResponseBody    
	@PostMapping("/IDCheck.do")
	public Map<String, Object> idCheck(String user_id) {
		Map<String,Object> idMap = new HashMap<>();
		int result = memberDao.idCheck(user_id);
		idMap.put("count", result);
		return idMap;
	}
}