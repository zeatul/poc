package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateMallUserRuntimeException  extends BasicRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6091145961805731096L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+7;

	public DuplicateMallUserRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
