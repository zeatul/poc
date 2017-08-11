package com.hawk.ecom.mall.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@NotNull("交付信息主键")
	private Integer orderDetailDeliveryDataId;
	
	@MallNotLogin
	private String userCode;
	
	@MallNotLogin
	private String storeCode;

}
