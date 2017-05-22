package com.hawk.ecom.sms.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class OverTimesException extends BasicRuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 186786255151215053L;

	public OverTimesException() {
		super(200002, "验证码每天发送不能超过5次");
	}

}
