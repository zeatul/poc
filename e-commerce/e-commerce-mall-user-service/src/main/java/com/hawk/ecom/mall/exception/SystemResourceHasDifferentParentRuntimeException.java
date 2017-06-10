package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SystemResourceHasDifferentParentRuntimeException extends BasicRuntimeException{


	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1582832413168807407L;
	final static int  code = ErrorCodeAllocation.MALL+13;
	
	public SystemResourceHasDifferentParentRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
