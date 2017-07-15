package com.hawk.ecom.pay.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class PayParam {
	
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
	
}
