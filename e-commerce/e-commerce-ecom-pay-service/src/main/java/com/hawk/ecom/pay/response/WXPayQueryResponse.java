package com.hawk.ecom.pay.response;

public class WXPayQueryResponse {
	
	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	/**
	 * 交易状态：
	 * SUCCESS—支付成功 
	 * REFUND—转入退款 
	 * NOTPAY—未支付 
	 * CLOSED—已关闭 
	 * REVOKED—已撤销（刷卡支付） 
	 * USERPAYING--用户支付中 
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 */
	private String tradeState;

}
