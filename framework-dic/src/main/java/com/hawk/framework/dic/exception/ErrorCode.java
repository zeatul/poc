package com.hawk.framework.dic.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		
		
		errorMap.put(10000,"数据字典未定义");
		errorMap.put(10001,"参数不能为空");
		errorMap.put(10002, "InvalidWord");
		
		
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
