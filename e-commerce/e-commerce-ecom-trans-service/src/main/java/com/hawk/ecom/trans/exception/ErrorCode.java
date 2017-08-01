package com.hawk.ecom.trans.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.TRANSACTION+0, "同一个订单的订单明细只能包括同一家店铺的商品");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+1, "商品未上架");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+2, "库存不足");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+4, "订单不存在");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+5, "订单不属于当前用户");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+6, "订单状态不符合当前操作要求");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+7, "申请支付订单已经超出支付截止日期");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+8, "商品交付信息未找到");
		errorMap.put(ErrorCodeAllocation.TRANSACTION+9, "同一个订单的订单明细不能重复");
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
