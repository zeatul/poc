package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenLogoutRuntimeException extends BasicRuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8744793999903128289L;
	
	
	private final static int  code = 100008;
	
	public TokenLogoutRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

	
}
