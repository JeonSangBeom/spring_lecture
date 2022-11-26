package com.jjang051.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// web.xml 대신 하는 것 java파일로 바꿔주는 역할
public class SpringConfig extends AbstractAnnotationConfigDispatcherServletInitializer { // 생성자에 상속해주기
	
	
	//프로젝트에 사용할 Beans 정의하기 위한 class지정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootAppContext.class}; // null로 된 것을 이렇게 배열로 바꿔주기 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletAppContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	// 한글 깨짐 방지....
	@Override
	protected Filter[]  getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
}