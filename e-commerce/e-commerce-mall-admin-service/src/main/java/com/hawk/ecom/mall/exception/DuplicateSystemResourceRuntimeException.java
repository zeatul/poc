package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateSystemResourceRuntimeException  extends BasicRuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736413687330114621L;
	private final static int  code = ErrorCodeAllocation.MALL_ADMIN+1;

	public DuplicateSystemResourceRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
