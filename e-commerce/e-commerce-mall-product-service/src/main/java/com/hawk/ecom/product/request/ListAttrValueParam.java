package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListAttrValueParam implements PageParam{


	public Integer getAttrValueStatus() {
		return attrValueStatus;
	}

	public void setAttrValueStatus(Integer attrValueStatus) {
		this.attrValueStatus = attrValueStatus;
	}

	public Integer getAttrNameId() {
		return attrNameId;
	}

	public void setAttrNameId(Integer attrNameId) {
		this.attrNameId = attrNameId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	 * 属性名主键
	 */
	private Integer attrNameId;
	
	/**
	 * 产品目录主键
	 */
	private Integer categoryId;
	
	
	/**
	 * 属性名状态
	 */
	private Integer attrValueStatus;
	

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
