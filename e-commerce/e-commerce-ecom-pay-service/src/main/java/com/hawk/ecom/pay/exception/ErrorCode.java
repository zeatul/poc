package com.hawk.ecom.pay.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.PAY+0, "支付单未找到");
		errorMap.put(ErrorCodeAllocation.PAY+1, "支付通知非法，[返回的支付金额与数据库不一致]");
		errorMap.put(ErrorCodeAllocation.PAY+2, "支付单状态不符合当前操作要求");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
