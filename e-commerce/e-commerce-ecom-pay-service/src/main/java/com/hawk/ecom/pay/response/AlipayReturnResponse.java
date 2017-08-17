package com.hawk.ecom.pay.response;

import com.hawk.framework.pub.web.ResponseData;

public class AlipayReturnResponse implements ResponseData{
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	private String outTradeNo;
	private String tradeNo;

}
