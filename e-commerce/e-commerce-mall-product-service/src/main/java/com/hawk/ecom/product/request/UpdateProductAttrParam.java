package com.hawk.ecom.product.request;

import com.hawk.framework.dic.validation.annotation.NotNull;


public class UpdateProductAttrParam {
	
	public Long getAttrNameId() {
		return attrNameId;
	}

	public void setAttrNameId(Long attrNameId) {
		this.attrNameId = attrNameId;
	}

	public Long getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}

	/**
	 * 属性名主键 attr_name_id
	 */
	@NotNull
	private Long attrNameId;
	
	/**
	 * 属性值主键 attr_value_id
	 */
	@NotNull
	private Long attrValueId;

}
