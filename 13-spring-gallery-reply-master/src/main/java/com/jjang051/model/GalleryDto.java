package com.jjang051.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Component
public class GalleryDto {
	private int no;
	private String title;
	private String contents;
	private String img;
	private String realImg;
	private String category;
	private String thumbnail;
	private int replyCount;
	
	//롬복 설치 이클립스 끄고 설치....
}
