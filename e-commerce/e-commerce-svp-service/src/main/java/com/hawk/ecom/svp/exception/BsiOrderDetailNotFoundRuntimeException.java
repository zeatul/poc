package com.hawk.ecom.svp.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class BsiOrderDetailNotFoundRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -628802184594739464L;
	
	private final static int  code = 300001;

	public BsiOrderDetailNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
