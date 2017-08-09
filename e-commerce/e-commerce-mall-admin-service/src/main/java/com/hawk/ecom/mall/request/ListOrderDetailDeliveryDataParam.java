package com.hawk.ecom.mall.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListOrderDetailDeliveryDataParam implements PageParam{
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	/**
	 * 订单主键
	 */
	private Integer orderId;
	
	/**
	 * 订单明细主键
	 */
	private Integer orderDetailId;

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
	
	
	@MallNotLogin
	private String userCode;
	
	@MallNotLogin
	private String storeCode;

}
