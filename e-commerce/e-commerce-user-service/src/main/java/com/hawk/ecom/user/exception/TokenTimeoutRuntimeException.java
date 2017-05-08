package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenTimeoutRuntimeException extends BasicRuntimeException{

	private final static int  code = 100004;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046858969384712312L;

	public TokenTimeoutRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
