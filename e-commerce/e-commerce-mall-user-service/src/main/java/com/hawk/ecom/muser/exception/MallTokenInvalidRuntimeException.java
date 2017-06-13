package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallTokenInvalidRuntimeException extends BasicRuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6382640182264138116L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+2;
	
	public MallTokenInvalidRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
