package com.hawk.framework.pub;

public class PageParam {
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
	private Integer pageIndex;
	private String order;
	private Integer pageRowCount;

}
