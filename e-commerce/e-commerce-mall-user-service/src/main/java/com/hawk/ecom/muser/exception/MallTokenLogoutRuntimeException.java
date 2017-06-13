package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallTokenLogoutRuntimeException extends BasicRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7762561087051875935L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+3;
	
	public MallTokenLogoutRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
