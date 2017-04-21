package com.hawk.ecom.pub.web;

import javax.servlet.http.HttpServletRequest;

import com.hawk.framework.pub.web.HttpRequestTools;

public class HttpRequestInfo {
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private String ip ;
	private String userAgent ;
	private String path ;
	private String method ;
	private String token ;
	
	public static HttpRequestInfo parse(HttpServletRequest request){
		String ip = HttpRequestTools.getIp(request);
		String userAgent = HttpRequestTools.getUserAgent(request);
		String path = request.getServletPath();
		String method = request.getMethod();
		String token = request.getParameter("t");
		HttpRequestInfo httpRequestInfo = new HttpRequestInfo();
		httpRequestInfo.setIp(ip);
		httpRequestInfo.setUserAgent(userAgent);
		httpRequestInfo.setPath(path);
		httpRequestInfo.setMethod(method);
		httpRequestInfo.setToken(token);
		return httpRequestInfo;
	}
}
