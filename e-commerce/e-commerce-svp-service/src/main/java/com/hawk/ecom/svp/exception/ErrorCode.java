package com.hawk.ecom.svp.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(300000, "代金券未找到");	
		errorMap.put(300001, "订单明细未找到");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
