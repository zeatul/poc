package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class UpdateAttrValueParam {
	

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrDisplayValue() {
		return attrDisplayValue;
	}

	public void setAttrDisplayValue(String attrDisplayValue) {
		this.attrDisplayValue = attrDisplayValue;
	}

	/**
	 * 主键 id
	 */
	@NotNull
	private Integer id;
	
	/**
	 * 属性值 attr_value
	 */
	private String attrValue;
	
	/**
	 * 属性值显示名 attr_display_value
	 */
	private String attrDisplayValue;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
