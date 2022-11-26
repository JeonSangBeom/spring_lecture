package com.jjang051.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// 1_db 데이터 불러와 주기
@Setter
@Getter
@ToString
@Component // rootappcontext에서 model 스캔하고 있어서 사용 가능
public class TodoDto {
	private int no;
	private String todo;
	private String pickedDate;
	private String done;
}