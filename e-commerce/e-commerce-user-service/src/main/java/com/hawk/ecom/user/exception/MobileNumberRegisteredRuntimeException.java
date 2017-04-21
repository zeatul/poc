package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class MobileNumberRegisteredRuntimeException extends BasicRuntimException{
	private final static int  code = 100000;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679769313211857373L;

	public MobileNumberRegisteredRuntimeException() {
		
		super(code,ErrorCode.getErrMsg(code));
	}

}
