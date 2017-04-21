package com.hawk.framework.pub.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

public class HttpRequestTools {

	/**
	 * 解析输入的请求参数
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> T parse(HttpServletRequest request, Class<T> clazz) throws IOException {

		String input = null;
		if (request.getMethod().equalsIgnoreCase("get")) {
			input = request.getParameter("query");
		} else {
			InputStream stream = request.getInputStream();
			input = IOUtils.toString(stream);
		}

		if (StringTools.isNullOrEmpty(input)) {
			return null;
		}

		T t = JsonTools.toObject(input, clazz);

		return t;

	}

	/**
	 * 获取请求的客户端ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getUserAgent(HttpServletRequest request){
		return request.getHeader("User-Agent");
	}

}
