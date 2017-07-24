package com.hawk.ecom.query.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class AttrNameNotFoundRuntimeException extends BasicRuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8308318537884209235L;
	private final static int  code = ErrorCodeAllocation.ECOM_QUERY+2;

	public AttrNameNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
