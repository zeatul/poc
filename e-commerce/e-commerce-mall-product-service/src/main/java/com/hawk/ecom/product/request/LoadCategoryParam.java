package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadCategoryParam {
	
	

	




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getOperatorCode() {
		return operatorCode;
	}




	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}




	/**
	 * 目录主键
	 */
	@NotNull
	private Long id;

	
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;


}
