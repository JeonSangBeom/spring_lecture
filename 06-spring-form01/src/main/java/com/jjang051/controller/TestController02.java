package com.jjang051.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jjang051.model.KeyValueDto;
import com.jjang051.model.SelectDto;

@Controller
public class TestController02 {
	//form에 있는 select 연습
	@GetMapping("/Test06.do")
	public String test06(SelectDto selectDto, Model model) {		
		// 두개를 한 번에 받아도 된다
		//처음 설정해둔 값으로 path의 value를 만들어둬서 selected사용 가능
		selectDto.setData01("data03");		
		selectDto.setData02("banana");
		selectDto.setData03("lion");
		selectDto.setData04("hippo");
		selectDto.setData05("giants");
		
		
		////////////////////////// 데이터 세팅
		
		//두군데 넣어 사용 / model, selectDto
		String selectList[] = {"apple","banana","kiwi"};
		selectDto.setDataList(selectList);
		model.addAttribute("selectList",selectList);
		
		
		//model에 넣기
		ArrayList<String> animalList = new ArrayList<>();
		animalList.add("hippo");
		animalList.add("tiger");
		animalList.add("lion");
		animalList.add("rabbit");		
		model.addAttribute("animalList",animalList);
		
		//변수 세개로 따로 값 설정
		KeyValueDto keyValueDto01 = new KeyValueDto();
		KeyValueDto keyValueDto02 = new KeyValueDto();
		KeyValueDto keyValueDto03 = new KeyValueDto();		
		keyValueDto01.setKey("사자");
		keyValueDto01.setValue("lion");
		keyValueDto02.setKey("호랑이");
		keyValueDto02.setValue("tiger");
		keyValueDto03.setKey("하마");
		keyValueDto03.setValue("hippo");
		
		ArrayList<KeyValueDto> animalList02 = new ArrayList<>();
		animalList02.add(keyValueDto01);
		animalList02.add(keyValueDto02);
		animalList02.add(keyValueDto03);
		
		model.addAttribute("animalList02",animalList02);
		
		return "test06"; // jsp 호출
	}
}


