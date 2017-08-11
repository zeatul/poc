package com.hawk.ecom.mall.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadOrderDetailParam {



	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
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
	 * 订单明细主键 order_detail_id
	 */
	@NotNull
	private Integer orderDetailId;
	

	
	
	
	@MallNotLogin
	private String userCode;
	
	@MallNotLogin
	private String storeCode;
}
