package com.hawk.ecom.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.pub.web.HttpRequestInfo;
import com.hawk.ecom.user.response.UserInfoResponse;
import com.hawk.ecom.user.service.LoginService;
import com.hawk.framework.utility.tools.JsonTools;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AccessInterceptor Start");	
		HttpRequestInfo httpRequestInfo= HttpRequestInfo.parse(request);
		logger.info(JsonTools.toJsonString(httpRequestInfo));
		AuthThreadLocal.setHttpRequestInfo(httpRequestInfo);
		String token = httpRequestInfo.getToken();
		UserInfoResponse userInfoResponse = loginService.loginInfo(token);
		
		if (userInfoResponse !=null){		
			AuthThreadLocal.setMobileNumber(userInfoResponse.getMobileNumber());
			AuthThreadLocal.setToken(token);
			AuthThreadLocal.setUserId(userInfoResponse.getUserId());	
			AuthThreadLocal.setUserCode(userInfoResponse.getUserCode());
		}else{
			AuthThreadLocal.setMobileNumber(null);
			AuthThreadLocal.setToken(null);
			AuthThreadLocal.setUserId(null);	
			AuthThreadLocal.setUserCode(null);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("AccessInterceptor Finished");
		super.afterCompletion(request, response, handler, ex);
	}
}
