package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallTokenTimeoutRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5103064564311628187L;

	private final static int  code = ErrorCodeAllocation.MALL+4;
	
	public MallTokenTimeoutRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
