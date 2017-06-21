package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class AttrNamePidIsDifferentWithAttrNameIdOfAttrValueRuntimeException extends BasicRuntimeException{


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3439461963834029480L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+26;
	
	public AttrNamePidIsDifferentWithAttrNameIdOfAttrValueRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
