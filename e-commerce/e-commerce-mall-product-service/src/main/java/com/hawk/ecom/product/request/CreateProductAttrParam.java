package com.hawk.ecom.product.request;

import com.hawk.framework.dic.validation.annotation.NotNull;


public class CreateProductAttrParam {
	
	public Integer getAttrNameId() {
		return attrNameId;
	}

	public void setAttrNameId(Integer  attrNameId) {
		this.attrNameId = attrNameId;
	}

	public Integer getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Integer  attrValueId) {
		this.attrValueId = attrValueId;
	}

	/**
	 * 属性名主键 attr_name_id
	 */
	@NotNull
	private Integer attrNameId;
	
	/**
	 * 属性值主键 attr_value_id
	 */
	@NotNull
	private Integer attrValueId;

}
