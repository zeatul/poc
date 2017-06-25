package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadPicParam {
	
	

	




	public Integer getId() {
		return id;
	}




	public void setId(Integer  id) {
		this.id = id;
	}




	public String getOperatorCode() {
		return operatorCode;
	}




	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}




	/**
	 * 产品主键
	 */
	@NotNull
	private Integer id;

	
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;


}
