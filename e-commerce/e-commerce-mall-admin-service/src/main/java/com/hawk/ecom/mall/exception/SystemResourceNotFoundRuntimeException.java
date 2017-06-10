package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SystemResourceNotFoundRuntimeException extends BasicRuntimeException{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7995749346620466840L;
	private final static int  code = ErrorCodeAllocation.MALL+10;
	
	public SystemResourceNotFoundRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
