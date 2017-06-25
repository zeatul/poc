package com.hawk.ecom.query.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class CommonExceptionResolver implements HandlerExceptionResolver{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private  static HttpServletResponse env(HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		return response;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			env(response).getWriter().print("CommonExceptionResolver");
		} catch (IOException e) {
			logger.error("CommonExceptionResolver error",e);
		}
		return null;
	}

}
