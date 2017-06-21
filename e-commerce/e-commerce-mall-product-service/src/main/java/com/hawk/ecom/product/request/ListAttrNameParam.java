package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListAttrNameParam implements PageParam{


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPvid() {
		return pvid;
	}

	public void setPvid(Integer pvid) {
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

	public Integer getAttrNameStatus() {
		return attrNameStatus;
	}

	public void setAttrNameStatus(Integer attrNameStatus) {
		this.attrNameStatus = attrNameStatus;
	}

	public Integer getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(Integer pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
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
	 * 属性值类型 attr_value_type
	 */
	private Integer attrValueType;	
	
	
	/**
	 * 属性名状态 attr_name_status
	 */
	private Integer attrNameStatus;
	
	/**
	 * 是否搜索 is_search
	 */
	private Integer isSearch;
	
	

	/**
	 * 页码
	 */
	private Integer pageIndex;
	
	/**
	 * 排序
	 */
	private String order;
	
	/**
	 * 每页数量
	 */
	private Integer pageRowCount;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
