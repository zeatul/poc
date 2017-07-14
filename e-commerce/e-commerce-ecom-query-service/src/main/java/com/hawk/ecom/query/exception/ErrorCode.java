package com.hawk.ecom.query.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.ECOM_QUERY+0, "未找到符合条件的属性值记录");
		errorMap.put(ErrorCodeAllocation.ECOM_QUERY+1, "未找到符合条件的产品Sku记录");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
