package com.hawk.framework.pub.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

public class HttpRequestTools {
	
	
	
	/**
	 * 解析输入的请求参数
	 * @param request
	 * @param clazz
	 * @return
	 * @throws IOException 
	 */
	public static <T> T  parse(HttpServletRequest request, Class<T> clazz) throws IOException  {

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
	
	

}
