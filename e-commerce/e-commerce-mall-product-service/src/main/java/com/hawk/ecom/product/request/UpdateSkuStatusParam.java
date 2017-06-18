package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class UpdateSkuStatusParam {
	

	public Integer getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(Integer skuStatus) {
		this.skuStatus = skuStatus;
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

	@NotEmpty("产品SKU主键集合")
	private List<Integer>   ids;
	
	@NotNull
	@ValidWord
	private Integer skuStatus;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
