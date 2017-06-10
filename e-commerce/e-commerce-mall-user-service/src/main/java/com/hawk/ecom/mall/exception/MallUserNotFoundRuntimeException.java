package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallUserNotFoundRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5562591703593683128L;
	private final static int  code = ErrorCodeAllocation.MALL+0;
	
	public MallUserNotFoundRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
