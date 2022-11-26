package com.jjang051.model;

import org.springframework.stereotype.Component;

@Component
//component 필수 이것을 사용하여야 autowired사용 가능
public class DataDto {
	private String data01;
	private String data02;
	
	public String getData01() {
		return data01;
	}
	public void setData01(String data01) {
		this.data01 = data01;
	}
	public String getData02() {
		return data02;
	}
	public void setData02(String data02) {
		this.data02 = data02;
	}
	@Override
	public String toString() {
		return "DataDto [data01=" + data01 + ", data02=" + data02 + "]";
	}
}