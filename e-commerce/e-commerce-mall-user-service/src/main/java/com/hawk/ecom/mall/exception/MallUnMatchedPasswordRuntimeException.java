package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallUnMatchedPasswordRuntimeException extends BasicRuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9058888533322283148L;
	private final static int  code = ErrorCodeAllocation.MALL+1;
	
	public MallUnMatchedPasswordRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}
}
