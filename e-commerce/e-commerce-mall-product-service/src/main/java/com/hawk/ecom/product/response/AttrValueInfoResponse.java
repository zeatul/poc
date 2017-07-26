package com.hawk.ecom.product.response;

import com.hawk.framework.pub.web.ResponseData;

public class AttrValueInfoResponse implements ResponseData{

	public String getAttrNameCode() {
		return attrNameCode;
	}

	public void setAttrNameCode(String attrNameCode) {
		this.attrNameCode = attrNameCode;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Integer getAttrValueStatus() {
		return attrValueStatus;
	}

	public void setAttrValueStatus(Integer attrValueStatus) {
		this.attrValueStatus = attrValueStatus;
	}

	/**
	 * 主键 id
	 */
	private Integer id;

	/**
	 * 产品目录主键 category_id
	 */
	private Integer categoryId;

	/**
	 * 属性名主键 attr_name_id
	 */
	private Integer attrNameId;
	
	/**
	 * 属性名编号 attr_name_code
	 */
	private String attrNameCode;
	
	/**
	 * 属性名名称 attr_name
	 */
	private String attrName;

	/**
	 * 属性值 attr_value
	 */
	private String attrValue;

	/**
	 * 属性值显示名 attr_display_value
	 */
	private String attrDisplayValue;

	/**
	 * 属性值状态 attr_value_status
	 */
	private Integer attrValueStatus;



}
