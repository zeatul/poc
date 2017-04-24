package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class UnMatchedVeriCodeRuntimeException extends BasicRuntimException{
	private final static int  code = 100003;
	/**
	 * 
	 */
	private static final long serialVersionUID = 680466207820870766L;

	public UnMatchedVeriCodeRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
