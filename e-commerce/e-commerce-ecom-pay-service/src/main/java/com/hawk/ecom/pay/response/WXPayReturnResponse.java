package com.hawk.ecom.pay.response;

import com.hawk.framework.pub.web.ResponseData;

public class WXPayReturnResponse implements ResponseData {
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private String result = "付款可能成功，脑残微信支付说";
	
	private String random ;
}
