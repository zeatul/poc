package com.hawk.ecom.svp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

public class HttpRequestToolsForSignature {
	
	public static <T> T parse(HttpServletRequest request, Class<T> clazz) throws IOException{
		String timestamp = request.getParameter("timestamp");
		if (StringTools.isNullOrEmpty(timestamp))
			throw new RuntimeException("timestamp is null");
		else{
			Date date = new Date(Long.parseLong(timestamp));
			if (Math.abs(date.getTime()- new Date().getTime()) > 1000*5*60)
				throw new RuntimeException("时间戳和当前时间相差大于5分钟");
		}
		
		String version = request.getParameter("version");
		if (!"1.0".equals(version))
			throw new RuntimeException("version is wrong");
		String store = request.getParameter("store");
		if (!"STO00000001".equals(store))
			throw new RuntimeException("store is wrong");
		String key = "&%12a9H6@";
		String reqhash = request.getParameter("reqhash");
		String input = null;
		if (request.getMethod().equalsIgnoreCase("get")) {
			input = request.getParameter("query");
		} else {
			InputStream stream = request.getInputStream();
			input = IOUtils.toString(stream);
		}
		
		String str = "";
		
		if (StringTools.isNullOrEmpty(input)) {
			str = str +timestamp+key;			
		}else{
			str = input+timestamp+key;
		}
		
		String mySignatue = DigestUtils.md5Hex(str);
		
		if (!mySignatue.equals(reqhash))
			throw new RuntimeException("签名错误");
		
		if (StringTools.isNullOrEmpty(input))
			return null;
		else{
			return JsonTools.toObject(input, clazz);
		}
	}

}
