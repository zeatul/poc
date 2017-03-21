package com.hawk.ecom.bsi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String,String> handleNotFound(Exception ex) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("ex", ex.getMessage());
		return map;
	}
}
