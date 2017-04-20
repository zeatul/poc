package com.hawk.ecom.sms.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class OverSpeedException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736076237305624787L;

	public OverSpeedException() {
		super(200001, "发送间隔不能低于1分钟");
	}

}
