package com.hawk.ecom.mall.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class CloseUnpaidOrderParam {
	
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

	/**
	 * 订单编号
	 */
	@NotNull
	private Integer orderId;
	
	@NotLogin
	private String userCode;

}
