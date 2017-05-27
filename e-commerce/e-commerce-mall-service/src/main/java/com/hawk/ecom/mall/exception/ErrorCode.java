package com.hawk.ecom.mall.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.MALL+0, "商城用户未找到");
		errorMap.put(ErrorCodeAllocation.MALL+1, "商城用户名和密码不匹配");
		errorMap.put(ErrorCodeAllocation.MALL+2, "商城用户登录token无效");
		errorMap.put(ErrorCodeAllocation.MALL+3, "商城用户登录token已注销");
		errorMap.put(ErrorCodeAllocation.MALL+4, "商城用户登录token已过期");
		errorMap.put(ErrorCodeAllocation.MALL+5, "商城用户登录token为空");
		errorMap.put(ErrorCodeAllocation.MALL+6, "商城用户未登录");
		errorMap.put(ErrorCodeAllocation.MALL+7, "商城用户已经存在，相同的手机号或者身份证件号");
		errorMap.put(ErrorCodeAllocation.MALL+8, "用户无权执行该操作");
		errorMap.put(ErrorCodeAllocation.MALL+9, "角色不存在");
		errorMap.put(ErrorCodeAllocation.MALL+10, "资源不存在");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
