package com.hawk.ecom.product.exception;

import java.util.HashMap;
import java.util.Map;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;

public class ErrorCode {
	
	private static Map<Integer,String> errorMap = new HashMap<Integer,String>();
	static{
		errorMap.put(ErrorCodeAllocation.PRODUCT+0, "产品目录未找到");
		errorMap.put(ErrorCodeAllocation.PRODUCT+1, "产品目录已经存在,产品目录编号唯一");
		errorMap.put(ErrorCodeAllocation.PRODUCT+2, "类型是最终分类的产品目录，不允许创建下级产品目录");
		errorMap.put(ErrorCodeAllocation.PRODUCT+3, "产品目录含有下级产品目录，不允许删除");
//		errorMap.put(ErrorCodeAllocation.PRODUCT+110, "产品未找到");
		
	}
	

	public static String getErrMsg(int errCode){
		return errorMap.get(errCode);
	}
}
