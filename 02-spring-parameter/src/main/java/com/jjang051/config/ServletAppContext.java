package com.jjang051.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//configuration로 사용을 하겠다
//EnableWebMvc쓸 수 있게끔 하겠다
//ComponentScan에 있는 것을 스캔해라 - spring container 띄우기 위한 사전 포석

@Configuration
@EnableWebMvc
@ComponentScan("com.jjang051.controller")
public class ServletAppContext implements WebMvcConfigurer {
	
	//jsp viewResolver 처리	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	// 정적 파일 찾기...
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	///resources - 폴더 이름 
	// 이 아래로 DB설정하는 것이 들어갈 예정
}