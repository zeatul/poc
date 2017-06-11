package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallRoleNotFoundRuntimeException extends BasicRuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1757308123203067492L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+9;
	
	public MallRoleNotFoundRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
