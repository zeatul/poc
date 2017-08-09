package com.hawk.ecom.mall.response;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getOrderCustomerMemo() {
		return orderCustomerMemo;
	}

	public void setOrderCustomerMemo(String orderCustomerMemo) {
		this.orderCustomerMemo = orderCustomerMemo;
	}

	public String getOrderStoreMemo() {
		return orderStoreMemo;
	}

	public void setOrderStoreMemo(String orderStoreMemo) {
		this.orderStoreMemo = orderStoreMemo;
	}

	public String getOrderStatusChangeMemo() {
		return orderStatusChangeMemo;
	}

	public void setOrderStatusChangeMemo(String orderStatusChangeMemo) {
		this.orderStatusChangeMemo = orderStatusChangeMemo;
	}

	public BigDecimal getOrderOriginalPrice() {
		return orderOriginalPrice;
	}

	public void setOrderOriginalPrice(BigDecimal orderOriginalPrice) {
		this.orderOriginalPrice = orderOriginalPrice;
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

	public BigDecimal getTotalFreightCharge() {
		return totalFreightCharge;
	}

	public void setTotalFreightCharge(BigDecimal totalFreightCharge) {
		this.totalFreightCharge = totalFreightCharge;
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

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 订单类型 order_type
	 */
	private Integer orderType;
	
	/**
	 * 订单状态 order_status
	 */
	private Integer orderStatus;
	
	/**
	 * 订单客户备注 order_customer_memo
	 */
	private String orderCustomerMemo;
	
	/**
	 * 订单商家备注 order_store_memo
	 */
	private String orderStoreMemo;
	
	/**
	 * 订单状态变更原因 order_status_change_memo
	 */
	private String orderStatusChangeMemo;
	
	/**
	 * 订单原价 order_original_price
	 */
	private BigDecimal orderOriginalPrice;
	
	/**
	 * 订单成交价 order_trans_price
	 */
	private BigDecimal orderTransPrice;
	
	/**
	 * 订单支付失效时间 order_pay_expire_time
	 */
	private Date orderPayExpireTime;
	
	/**
	 * 总运费 total_freight_charge
	 */
	private BigDecimal totalFreightCharge;
	
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
	
	
	
	/**
	 * 创建者 create_user_code
	 */
	private String createUserCode;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新者 update_user_code
	 */
	private String updateUserCode;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	
	
}
