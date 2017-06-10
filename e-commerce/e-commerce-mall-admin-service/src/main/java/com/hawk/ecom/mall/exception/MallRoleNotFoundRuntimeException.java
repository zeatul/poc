package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallRoleNotFoundRuntimeException extends BasicRuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1757308123203067492L;
	private final static int  code = ErrorCodeAllocation.MALL+9;
	
	public MallRoleNotFoundRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
