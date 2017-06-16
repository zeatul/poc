package com.hawk.ecom.muser.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.MALL_USER+0, "商城用户未找到");
		errorMap.put(ErrorCodeAllocation.MALL_USER+1, "商城用户名和密码不匹配");
		errorMap.put(ErrorCodeAllocation.MALL_USER+2, "商城用户登录token无效");
		errorMap.put(ErrorCodeAllocation.MALL_USER+3, "商城用户登录token已注销");
		errorMap.put(ErrorCodeAllocation.MALL_USER+4, "商城用户登录token已过期");
		errorMap.put(ErrorCodeAllocation.MALL_USER+5, "商城用户登录token为空");
		errorMap.put(ErrorCodeAllocation.MALL_USER+6, "商城用户未登录");
		errorMap.put(ErrorCodeAllocation.MALL_USER+7, "商城用户已经存在,相同的手机号或者身份证件号");
		errorMap.put(ErrorCodeAllocation.MALL_USER+8, "用户无权执行该操作");
		errorMap.put(ErrorCodeAllocation.MALL_USER+9, "角色不存在");
		errorMap.put(ErrorCodeAllocation.MALL_USER+10, "用户被禁止");
		errorMap.put(ErrorCodeAllocation.MALL_USER+11, "系统保留用户，不能被删除");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
