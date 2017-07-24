package com.hawk.ecom.trans.response;

import com.hawk.framework.pub.web.ResponseData;

public class ChargeDataNotifyResponse implements ResponseData{
	public static ChargeDataNotifyResponse getInstance() {
		return instance;
	}

	public String getCode() {
		return code;
	}


	public String getMsg() {
		return msg;
	}

	

	// 返回报文：
	// {
	// "code": "ok",
	// "msg": "接收成功"
	// }
	private final String code;
	private final String msg;
	
	 
	
	private ChargeDataNotifyResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private static ChargeDataNotifyResponse instance = new  ChargeDataNotifyResponse("ok","接收成功");

}
