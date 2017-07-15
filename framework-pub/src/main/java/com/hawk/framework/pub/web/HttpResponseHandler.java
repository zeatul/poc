package com.hawk.framework.pub.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



public class HttpResponseHandler {
	
	private  static HttpServletResponse env(HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		return response;
	}
	
//	public static void handle(HttpServletResponse response,IResponse result) throws Exception {
//
//		String r = result.toJson();
//		env(response).getWriter().print(r);
//
//	}
	
	
	/**
	 * 直接向前台输出字符串
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void printASAP(HttpServletResponse response,String result) throws IOException{
		
		PrintWriter writer = env(response).getWriter();  
        writer.write(result);  
        writer.flush();
	}

}
