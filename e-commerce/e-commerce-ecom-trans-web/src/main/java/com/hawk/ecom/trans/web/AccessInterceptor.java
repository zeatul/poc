package com.hawk.ecom.query.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hawk.ecom.pub.web.HttpRequestInfo;
import com.hawk.framework.utility.tools.JsonTools;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpRequestInfo httpRequestInfo= HttpRequestInfo.parse(request);
		logger.info("AccessInterceptor : {}",JsonTools.toJsonString(httpRequestInfo));
		String version = request.getParameter("version");
		if (version == null || !"1.0".equals(version)){
			throw new RuntimeException("version不对");
		}
	
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
