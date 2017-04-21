package com.hawk.ecom.user.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(100000," 手机号已经注册");
		errorMap.put(100001, "用户不存在");
		errorMap.put(100002, "账号密码不匹配");
		errorMap.put(100003, "验证码不正确");
		
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
