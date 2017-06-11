package com.hawk.ecom.mall.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{		
		errorMap.put(ErrorCodeAllocation.MALL_ADMIN+0, "资源不存在");
		errorMap.put(ErrorCodeAllocation.MALL_ADMIN+1, "资源已存在,相同的nodeCode");
		errorMap.put(ErrorCodeAllocation.MALL_ADMIN+2, "当前操作资源有子节点资源存在");
		errorMap.put(ErrorCodeAllocation.MALL_ADMIN+3, "当前操作所有资源节点的父节点必须相同");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
