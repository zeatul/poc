package com.hawk.ecom.pub.web;

import javax.servlet.http.HttpServletRequest;

import com.hawk.framework.pub.web.HttpRequestTools;

public final class HttpRequestInfo {

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getPath() {
		return path;
	}

	public String getMethod() {
		return method;
	}

	public String getToken() {
		return token;
	}

	public HttpRequestInfo(String ip, String userAgent, String path, String method, String token) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.path = path;
		this.method = method;
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
		HttpRequestInfo httpRequestInfo = new HttpRequestInfo(ip,userAgent,path,method,token);
	
		return httpRequestInfo;
	}
}
