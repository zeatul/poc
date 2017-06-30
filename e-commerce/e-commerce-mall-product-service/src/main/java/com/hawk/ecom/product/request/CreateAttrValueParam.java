package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

/**
 * 创建产品属性
 * @author Administrator
 *
 */
public class CreateAttrValueParam {


	public String getAttrDisplayEnValue() {
		return attrDisplayEnValue;
	}




	public void setAttrDisplayEnValue(String attrDisplayEnValue) {
		this.attrDisplayEnValue = attrDisplayEnValue;
	}




	public Integer getAttrNameId() {
		return attrNameId;
	}




	public void setAttrNameId(Integer attrNameId) {
		this.attrNameId = attrNameId;
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




	public String getOperatorCode() {
		return operatorCode;
	}




	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}




	/**
	 * 属性名主键 attr_name_id
	 */
	@NotNull
	private Integer attrNameId;
	
	/**
	 * 属性值 attr_value
	 */
	@NotEmpty
	private String attrValue;
	
	/**
	 * 属性值显示名 attr_display_value
	 */
	private String attrDisplayValue;
	

	/**
	 * 属性值显示英文名称 attr_display_en_value
	 */
	private String attrDisplayEnValue;

	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
