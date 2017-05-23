package com.hawk.ecom.mall.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class MallListUserParam implements PageParam{
	

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
	@JsonIgnore
	private String operatorCode;

}
