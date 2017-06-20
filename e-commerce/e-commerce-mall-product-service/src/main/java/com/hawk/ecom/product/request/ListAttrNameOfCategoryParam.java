package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class ListAttrNameOfCategoryParam {
	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer  categoryId) {
		this.categoryId = categoryId;
	}


	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
	
	@NotNull
	private Integer categoryId;
}
