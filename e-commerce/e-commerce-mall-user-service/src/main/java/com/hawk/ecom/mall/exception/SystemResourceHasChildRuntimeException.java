package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SystemResourceHasChildRuntimeException extends BasicRuntimeException{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8181615688165017408L;
	private final static int  code = ErrorCodeAllocation.MALL+12;
	
	public SystemResourceHasChildRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
