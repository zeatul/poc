package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenTimeoutException extends BasicRuntimeException{

	private final static int  code = 100005;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046858969384712312L;

	public TokenTimeoutException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
