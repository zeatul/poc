package com.hawk.ecom.trans.response;

import java.math.BigDecimal;
import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class OrderInfoResponse implements ResponseData{
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTransPrice() {
		return orderTransPrice;
	}

	public void setOrderTransPrice(BigDecimal orderTransPrice) {
		this.orderTransPrice = orderTransPrice;
	}

	public Date getOrderPayExpireTime() {
		return orderPayExpireTime;
	}

	public void setOrderPayExpireTime(Date orderPayExpireTime) {
		this.orderPayExpireTime = orderPayExpireTime;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 订单类型 order_type
	 */
	private Integer orderType;
	
	/**
	 * 订单状态 order_status
	 */
	private Integer orderStatus;
	
	/**
	 * 订单成交价 order_trans_price
	 */
	private BigDecimal orderTransPrice;
	
	/**
	 * 订单支付失效时间 order_pay_expire_time
	 */
	private Date orderPayExpireTime;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * 订单描述 order_desc
	 */
	private String orderDesc;
	
	/**
	 * 支付方式 pay_type
	 */
	private Integer payType;
	
	
}
