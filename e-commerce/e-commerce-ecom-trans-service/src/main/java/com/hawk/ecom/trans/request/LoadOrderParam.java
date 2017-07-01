package com.hawk.ecom.trans.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadOrderParam {
	


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
	 * 订单主键 order_id
	 */
	@NotNull
	private Integer orderId;
	

	
	@NotLogin
	private String userCode;

}
