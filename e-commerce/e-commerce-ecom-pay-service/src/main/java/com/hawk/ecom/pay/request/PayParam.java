package com.hawk.ecom.pay.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class PayParam {
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public boolean isWap() {
		return isWap;
	}

	public void setWap(boolean isWap) {
		this.isWap = isWap;
	}

	public String getPaymentCategoryCode() {
		return paymentCategoryCode;
	}

	public void setPaymentCategoryCode(String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@NotNull
	private Integer orderId;
	
	@NotLogin
	private String userCode;
	
	/**
	 * 支付分类，决定了系统调用哪个支付平台
	 */
	private String paymentCategoryCode ;
	
	private boolean isWap;
	
	/**
	 * 微信支付需要ip地址
	 */
	private String ip;
	
	/**
	 * 微信公众号支付需要openid
	 */
	private String openid;
	
}
