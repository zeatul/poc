package com.hawk.ecom.trans.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadOrderDetailDeliveryDataParam {
	
	public Integer getOrderDetailDeliveryDataId() {
		return orderDetailDeliveryDataId;
	}

	public void setOrderDetailDeliveryDataId(Integer orderDetailDeliveryDataId) {
		this.orderDetailDeliveryDataId = orderDetailDeliveryDataId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@NotNull("交付信息主键")
	private Integer orderDetailDeliveryDataId;
	
	@NotLogin
	private String userCode;

}
