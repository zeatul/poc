package com.flow.client;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 向渠道返回的流量订单结果
 * 
 * @author Administrator
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderResponse {

	private String resultCode;
	private String orderNo;
	private String clientOrderNo;
	private String sign;
	private String errorMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
