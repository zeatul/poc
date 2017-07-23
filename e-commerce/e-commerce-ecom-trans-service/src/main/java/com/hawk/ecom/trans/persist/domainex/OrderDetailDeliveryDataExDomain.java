package com.hawk.ecom.trans.persist.domainex;

public class OrderDetailDeliveryDataExDomain {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getOuterProductId() {
		return outerProductId;
	}

	public void setOuterProductId(String outerProductId) {
		this.outerProductId = outerProductId;
	}

	public String getBenefMobileNumber() {
		return benefMobileNumber;
	}

	public void setBenefMobileNumber(String benefMobileNumber) {
		this.benefMobileNumber = benefMobileNumber;
	}

	public Integer getOrderDetailStatus() {
		return orderDetailStatus;
	}

	public void setOrderDetailStatus(Integer orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 订单明细主键 order_detail_id
	 */
	private Integer orderDetailId;

	/**
	 * 订单主键 order_id
	 */
	private Integer orderId;
	
	/**
	 * 明细状态 order_detail_status
	 */
	private Integer orderDetailStatus; 
	
	private Integer id;
	
	private String taskCode;
	
	private String outerProductId;
	
	private String benefMobileNumber;

}
