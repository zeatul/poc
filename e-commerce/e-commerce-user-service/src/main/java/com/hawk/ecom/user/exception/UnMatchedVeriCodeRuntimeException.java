package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UnMatchedVeriCodeRuntimeException extends BasicRuntimeException{
	private final static int  code = 100003;
	/**
	 * 
	 */
	private static final long serialVersionUID = 680466207820870766L;

	public UnMatchedVeriCodeRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
