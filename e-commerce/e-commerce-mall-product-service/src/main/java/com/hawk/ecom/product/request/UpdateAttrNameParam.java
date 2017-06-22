package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class UpdateAttrNameParam {
	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public Integer getAttrNameBusinessType() {
		return attrNameBusinessType;
	}

	public void setAttrNameBusinessType(Integer attrNameBusinessType) {
		this.attrNameBusinessType = attrNameBusinessType;
	}

	

	public Integer getAttrValueType() {
		return attrValueType;
	}

	public void setAttrValueType(Integer attrValueType) {
		this.attrValueType = attrValueType;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 主键 id
	 */
	@NotNull
	private Integer id;
	
		
	/**
	 * 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 * 属性有值后
	 */
	private Integer attrNameBusinessType;
	
	/**
	 * 属性值类型 attr_value_type
	 */
	private Integer attrValueType;
	
	/**
	 * 属性名名称 attr_name
	 */
	private String attrName;
	
	
	
	/**
	 * 是否搜索 is_search
	 */
	private Integer isSearch;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	

}
