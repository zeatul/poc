package com.hawk.framework.pub.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



public class HttpResponseHandler {
	

	
	
	/**
	 * 直接向前台输出Json字符串
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void printJsonASAP(HttpServletResponse response,String result) throws IOException{
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();  
        writer.write(result);  
        writer.flush();
	}
	
	/**
	 * 直接向前台输出Html網頁
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void printHtmlASAP(HttpServletResponse response,String result) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();  
        writer.write(result);  
        writer.flush();
	}

}
