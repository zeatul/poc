package com.hawk.ecom.trans.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.TRANSACTION+0, "订单明细只能包括同一家店铺的商品");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+1, "商品未上架");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+2, "库存不足");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+4, "订单不存在");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+5, "订单不属于当前用户");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
