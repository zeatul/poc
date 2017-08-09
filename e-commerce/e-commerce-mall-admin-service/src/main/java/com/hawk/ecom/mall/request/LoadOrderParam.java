package com.hawk.ecom.mall.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	/**
	 * 订单主键 order_id
	 */
	@NotNull
	private Integer orderId;
	
	@MallNotLogin
	private String userCode;
	
	@MallNotLogin
	private String storeCode;
}
