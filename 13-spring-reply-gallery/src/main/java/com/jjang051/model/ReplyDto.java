package com.jjang051.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
// 분리된 테이블이기에 새로 생성(gallery reply 와 reply)
public class ReplyDto {
	private int no;
	private int boardId;
	private String reply;
}