package com.hawk.ecom.svp.controller;

import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hawk.framework.pub.web.ExceptionResponseData;
import com.hawk.framework.pub.web.FailureResponse;

@RestControllerAdvice
public class AppWideExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public FailureResponse<ExceptionResponseData> handleException(Exception ex) {
		logger.error("error", ex);
		ExceptionResponseData e = new ExceptionResponseData();
		e.setErrCode("-1");
//		e.setException(ex.getClass().getName());
		e.setMessage(ex.getMessage());
		return FailureResponse.build(e);
	}
	
	@ExceptionHandler(UndeclaredThrowableException.class)
	public  FailureResponse<ExceptionResponseData> handle(UndeclaredThrowableException ex){
		logger.error("error", ex);
		ExceptionResponseData e = new ExceptionResponseData();
		e.setErrCode("-1");
//		e.setException(ex.getClass().getName());
		e.setMessage(ex.getCause().getMessage());
		return FailureResponse.build(e);
	}
}
