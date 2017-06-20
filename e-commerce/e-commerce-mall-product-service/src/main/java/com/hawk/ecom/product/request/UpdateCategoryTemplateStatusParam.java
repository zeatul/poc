package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class UpdateCategoryTemplateStatusParam {
	

	public Integer getCategoryTemplateStatus() {
		return categoryTemplateStatus;
	}

	public void setCategoryTemplateStatus(Integer categoryTemplateStatus) {
		this.categoryTemplateStatus = categoryTemplateStatus;
	}

	public List<Integer>   getIds() {
		return ids;
	}

	public void setIds(List<Integer>   ids) {
		this.ids = ids;
	}

	

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@NotEmpty("产品目录主键集合")
	private List<Integer>   ids;
	
	@NotNull
	@ValidWord
	private Integer categoryTemplateStatus;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
