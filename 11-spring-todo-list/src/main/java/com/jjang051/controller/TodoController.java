package com.jjang051.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjang051.model.TodoDao;
import com.jjang051.model.TodoDto;

// dto 에서 지지고 볶고 하는 것은 다 여기서 한다

@Controller
public class TodoController {
	// 자동 주입
	@Autowired
	TodoDao todoDao;
	
	@Autowired
	TodoDto todoDto;
	
	//json으로 떨굴 것
	@RequestMapping("/Insert.do") // ajax에서 insert.do로 넘겨 보낸 것
	@ResponseBody    //JSON으로 데이터 넘길때
	public Map<String,Object> insertTodo(@RequestBody TodoDto todoDto) {
		// 데이터(맞춰둔 상태)를 받을때 @RequestBody로 받으면 스트링(쿼리 스트링)형태로 떨어진다(한꺼번에) - 이것으로 ajax사용 가능
		// 리턴은 map으로 그리고 모든것을 다 받을 수 있는 object를 던져둔다
		System.out.println("todoDto===="+todoDto);
		Map<String,Object> hashMap = new HashMap<>();//@ResponseBody로 키와 값을 넣어주면 json으로 리턴해준다
		List<TodoDto> todoList = null;
		String pickedDate = todoDto.getPickedDate();// 아래 if에 있는 pickedDate 넘기는 법
		int result = todoDao.insertTodo(todoDto);// 만들어 둔것 가져오기
		if(result > 0) {
			todoList = todoDao.getAllList(pickedDate);
			hashMap.put("todoList", todoList);// 키와 값
		}
		return hashMap;// 바로 todoList로 떨궈도 상관은 없다(그러면 키값없이 배열만 넘어오게 된다 res(javascript에 정의해둠)자체가 배열이 된다)
	}
	
	// 리스트로 JSON리턴 해주기
	@RequestMapping("/InsertList.do")
	@ResponseBody    //JSON으로 데이터 넘길때
	public List<TodoDto> insertTodoList(@RequestBody TodoDto todoDto) {
		System.out.println("todoDto===="+todoDto);
		Map<String,Object> hashMap = new HashMap<>();
		List<TodoDto> todoList = null;
		String pickedDate = todoDto.getPickedDate();
		int result = todoDao.insertTodo(todoDto);
		if(result > 0) {
			todoList = todoDao.getAllList(pickedDate);
			hashMap.put("todoList", todoList);
		}
		return todoList;
	}
	
	// 날짜에 해당되는 데이터 받기.
	@RequestMapping("/List.do")
	@ResponseBody//json
	public Map<String,Object> getTodoList(@RequestBody TodoDto todoDto) {	
		Map<String,Object> hashMap= new HashMap<>();
		String pickedDate = todoDto.getPickedDate();
		// 해당되는 날짜의 데이터를 뽑아서 던져주면 된다. 20220308
		List<TodoDto> todoList = todoDao.getAllList(pickedDate);// todoDao에 던져준다 pickedDate을 
		hashMap.put("todoList", todoList);
		return hashMap;
	}
	
	
	// 3) 삭제.
	@RequestMapping("/Delete.do")
	@ResponseBody
	public Map<String,Object> deleteTodo(@RequestBody TodoDto todoDto) {//@RequestBody로 받기
		Map<String,Object> hashMap= new HashMap<>();
		int no = todoDto.getNo();
		int result = todoDao.deleteTodo(no);
		hashMap.put("result", result);  //{result:1} - 키 값으로 이렇게 떨어질 것
		return hashMap;
	}
	
	@RequestMapping("/Update.do")
	@ResponseBody
	public Map<String,Object> updateTodo(@RequestBody TodoDto todoDto) {
		Map<String,Object> hashMap= new HashMap<>();
		//int no = todoDto.getNo(); - dto자체가 던져저서 필요 없다
		int result = todoDao.updateTodo(todoDto);
		hashMap.put("result", result);  //{result:1}
		return hashMap;
	}
}










