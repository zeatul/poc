package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class ExchangeCategoryOrderParam {
	

	


	public Integer getCategoryIdA() {
		return categoryIdA;
	}



	public void setCategoryIdA(Integer categoryIdA) {
		this.categoryIdA = categoryIdA;
	}



	public Integer getCategoryIdB() {
		return categoryIdB;
	}



	public void setCategoryIdB(Integer categoryIdB) {
		this.categoryIdB = categoryIdB;
	}



	public String getOperatorCode() {
		return operatorCode;
	}



	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}



	@NotNull("categoryId")
	private Integer categoryIdA;
	
	@NotNull("categoryId")
	private Integer categoryIdB;
	
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
