package com.hawk.ecom.user.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

public class SsoToolsForSignature {
	
	private static Logger logger = LoggerFactory.getLogger(SsoToolsForSignature.class);
	
	private static Map<String,String> appMap = new HashMap<String,String>();
	static{
		/**
		 * 铁鞋  
		 */
		appMap.put("580fb1dcd2ee427387cc580d545a6405", "0865098d17844bf5978afafa8dc3d058");
	}
	
	public static <T> T parse(HttpServletRequest request, Class<T> clazz) throws IOException {
		String timestamp = request.getParameter("timestamp");
		String appid = request.getParameter("appid");
		String version = request.getParameter("version");
		String reqhash = request.getParameter("reqhash");
		String input = null;
		if (request.getMethod().equalsIgnoreCase("get")) {
			input = request.getParameter("req");
			if (input!=null){
				input = URLDecoder.decode(input, "utf-8");
			}
		} else {
			InputStream stream = request.getInputStream();
			input = IOUtils.toString(stream);
		}
		
		logger.info("Sso request: timestamp={},appid={},version={},reqhash={},input={}",timestamp,appid,version,reqhash,input);
		
		if (StringTools.isNullOrEmpty(timestamp))
			throw new RuntimeException("timestamp is null");
		else {
			Date date = new Date(Long.parseLong(timestamp));
			if (Math.abs(date.getTime() - new Date().getTime()) > 1000 * 5 * 60)
				throw new RuntimeException("时间戳和当前时间相差大于5分钟");
		}
		
		
		if(StringTools.isNullOrEmpty(appid))
			throw new RuntimeException("appid is empty");
		
		String appkey = appMap.get(appid);
		if(StringTools.isNullOrEmpty(appkey))
			throw new RuntimeException("appid is illegal");

		
		if (!"1.0".equals(version))
			throw new RuntimeException("version is wrong");

		

		

		String str = "";

		if (StringTools.isNullOrEmpty(input)) {
			str = str+version + timestamp +appid+ appkey;
		} else {
			str = input+version + timestamp+appid + appkey;
		}

		String mySignatue = DigestUtils.md5Hex(str);

		if (!mySignatue.equals(reqhash))
			throw new RuntimeException("签名错误");

		if (StringTools.isNullOrEmpty(input))
			return null;
		else {
			return JsonTools.toObject(input, clazz);
		}
	}
	
	public static void main(String[] args){
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	}
}
