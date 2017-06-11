package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class RemoveCategoryParam {


	


	public List<Long> getIds() {
		return ids;
	}


	public void setIds(List<Long> ids) {
		this.ids = ids;
	}


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	/**
	 * 要删除的产品目录集合
	 */
	@NotEmpty("产品目录主键集合")
	private List<Long> ids;
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
