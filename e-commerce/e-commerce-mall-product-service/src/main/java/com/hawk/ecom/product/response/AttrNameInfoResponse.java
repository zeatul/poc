package com.hawk.ecom.product.response;

import com.hawk.framework.pub.web.ResponseData;

public class AttrNameInfoResponse implements ResponseData{

	public String getAttrNameCode() {
		return attrNameCode;
	}

	public void setAttrNameCode(String attrNameCode) {
		this.attrNameCode = attrNameCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer  categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer  pid) {
		this.pid = pid;
	}

	public Integer getPvid() {
		return pvid;
	}

	public void setPvid(Integer  pvid) {
		this.pvid = pvid;
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

	public Integer getAttrNameStatus() {
		return attrNameStatus;
	}

	public void setAttrNameStatus(Integer attrNameStatus) {
		this.attrNameStatus = attrNameStatus;
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
	 * 父属性名主键 pid
	 */
	private Integer pid;
	
	/**
	 * 父属性值主键 pvid
	 */
	private Integer pvid;
	
	/**
	 * 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 */
	private Integer attrNameBusinessType;
	
	/**
	 * 属性名编号 attr_name_code
	 */
	private String attrNameCode;
	
	
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
	 * 属性名状态 attr_name_status
	 */
	private Integer attrNameStatus;
	
	
}
