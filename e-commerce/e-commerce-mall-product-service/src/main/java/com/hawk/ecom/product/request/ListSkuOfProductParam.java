package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class ListSkuOfProductParam {
	
	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer  productId) {
		this.productId = productId;
	}


	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
	
	@NotNull
	private Integer productId;

}
