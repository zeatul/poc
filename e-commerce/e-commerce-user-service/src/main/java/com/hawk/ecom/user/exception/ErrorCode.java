package com.hawk.ecom.user.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(100000, "手机号已经注册");
		errorMap.put(100001, "用户不存在");
		errorMap.put(100002, "账号密码不匹配");
//		errorMap.put(100003, "验证码不正确");
		errorMap.put(100004, "登陆已过期");
		errorMap.put(100005, "登陆票据为空");
		errorMap.put(100006, "登陆票据非法");
		errorMap.put(100007, "用户未登录");
		errorMap.put(100008, "登录已经注销");
		
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
