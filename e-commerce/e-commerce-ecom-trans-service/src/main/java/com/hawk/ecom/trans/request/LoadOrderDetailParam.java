package com.hawk.ecom.trans.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.pub.sql.PageParam;

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


	@NotNull
	private Integer orderDetailId;
	
	
	@NotLogin
	private String userCode;

}
