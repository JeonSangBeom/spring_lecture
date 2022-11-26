package com.jjang051.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.jjang051.model.BoardDto;
import com.jjang051.model.DataDto02;

@Configuration
public class RootAppContext {
	
	//직접 등록하는 방법(필요한 것들을 여기다가 보통 만들어 사용한다)
	//기본 세팅은 servleappcontext에 - scan 및 component
	@Bean
	public DataDto02 dataDto02() {
		return new DataDto02();
	}
	
	@Bean
	public BoardDto boardDto() {
		return new BoardDto();
	}
	
}