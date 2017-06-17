package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class UpdateCategoryStatusParam {
	

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Integer getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@NotEmpty("产品目录主键集合")
	private List<Long> ids;
	
	@NotNull
	@ValidWord
	private Integer categoryStatus;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
