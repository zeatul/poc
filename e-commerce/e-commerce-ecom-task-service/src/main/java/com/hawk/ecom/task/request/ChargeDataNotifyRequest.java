package com.hawk.ecom.task.request;

public class ChargeDataNotifyRequest {
	// 请求报文：
	// {
	// "status": "0007",
	// "orderNo": "12334354545454",
	// "cstmOrderNo": "13512345631",
	// "msg ": "订购成功"
	// }
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCstmOrderNo() {
		return cstmOrderNo;
	}
	public void setCstmOrderNo(String cstmOrderNo) {
		this.cstmOrderNo = cstmOrderNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private String status;
	private String orderNo;
	private String cstmOrderNo;
	private String msg;
}
