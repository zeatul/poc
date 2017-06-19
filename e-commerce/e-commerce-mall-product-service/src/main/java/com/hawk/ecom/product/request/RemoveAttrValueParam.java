package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class RemoveAttrValueParam {

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

	@NotEmpty("属性名主键集合")
	List<Integer>   ids;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
