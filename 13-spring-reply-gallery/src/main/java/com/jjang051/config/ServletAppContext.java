package com.jjang051.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		registry.addResourceHandler("/**").addResourceLocations("/resources/"); // js, css, img 등등 처리하기 위한거
		registry.addResourceHandler("/galleryImage/**").addResourceLocations("file:///C:/galleryImage/"); // fileupload용
		///galleryImage/**에 들어오는(시작하는) 모든 것들은 file:///C:/galleryImage/ 이 밑에 여기서 처리 하겠다
	}
	@Bean // 주입
	public StandardServletMultipartResolver multipartResolver() { // spring에서 제공 하는 멀티티 파트
		return new StandardServletMultipartResolver();
	}
}


