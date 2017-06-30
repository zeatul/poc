package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class UpdateCategoryVariantStatusParam {
	

	

	public Integer getCategoryVariantStatus() {
		return categoryVariantStatus;
	}

	public void setCategoryVariantStatus(Integer categoryVariantStatus) {
		this.categoryVariantStatus = categoryVariantStatus;
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
	/**
	 * 最终产品目录变式状态 category_variant_status
	 */
	private Integer categoryVariantStatus;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
