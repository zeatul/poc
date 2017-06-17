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


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
	
	@NotNull
	private Long productId;

}
