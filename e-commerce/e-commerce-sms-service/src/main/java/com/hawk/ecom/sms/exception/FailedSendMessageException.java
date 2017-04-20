package com.hawk.ecom.sms.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class FailedSendMessageException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8854362980739230098L;

	public FailedSendMessageException(String code, String message) {
		super(200000, "发送短信失败,code="+code+",cause="+message);
	}
	
	

}
