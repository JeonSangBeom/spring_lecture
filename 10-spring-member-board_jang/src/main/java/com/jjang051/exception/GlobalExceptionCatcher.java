package com.jjang051.exception;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionCatcher {
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 200 -> 500
	public String notFound(NoHandlerFoundException ex) {
		System.out.println("not found error");
		return "error/customer_404";
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
//		m.addAttribute("ex", ex);
		System.out.println("controller 첫번째 에러");
		System.out.println("m="+m);
		return "error/customer_404";
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200 -> 500
	public String catcher(Exception ex, Model m) {
		System.out.println("controller 두번째 에러");
		System.out.println("m="+m);
//		m.addAttribute("ex", ex);

		return "error/error";
	}
	
	
}
