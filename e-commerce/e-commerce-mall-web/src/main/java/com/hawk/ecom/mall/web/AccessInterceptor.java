package com.hawk.ecom.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.mall.service.MallUserService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.pub.web.HttpRequestInfo;
import com.hawk.framework.utility.tools.JsonTools;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MallUserService mallUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpRequestInfo httpRequestInfo= HttpRequestInfo.parse(request);
		logger.info("AccessInterceptor : {}",JsonTools.toJsonString(httpRequestInfo));
		AuthThreadLocal.setHttpRequestInfo(httpRequestInfo);
		String token = httpRequestInfo.getToken();
		MallUserDomain mallUserDomain = mallUserService.loginInfo(token);
		
		String version = request.getParameter("version");
		if (version == null || !"1.0".equals(version)){
			throw new RuntimeException("version不对");
		}
		
		if (mallUserDomain !=null){		
			AuthThreadLocal.setMobileNumber(mallUserDomain.getMobileNumber());
			AuthThreadLocal.setToken(token);
			AuthThreadLocal.setUserId(mallUserDomain.getId());	
			AuthThreadLocal.setUserCode(mallUserDomain.getUserCode());
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
		super.afterCompletion(request, response, handler, ex);
	}
}
