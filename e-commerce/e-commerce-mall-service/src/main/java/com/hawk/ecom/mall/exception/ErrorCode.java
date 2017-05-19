package com.hawk.ecom.mall.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.MALL+0, "商城用户未找到");
		errorMap.put(ErrorCodeAllocation.MALL+1, "商城用户名和密码不匹配");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
