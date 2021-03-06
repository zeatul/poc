package com.hawk.ecom.trans.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 订单
 * The class is mapped to the table t_tra_order 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
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
	 * 订单版本号 order_version
	 */
	private Integer orderVersion;
	
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
	
	/**
	 * 删除者 delete_user_code
	 */
	private String deleteUserCode;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 商户编号 store_code
	 */
	public String getStoreCode(){
		return storeCode;
	}
	
	/**
	 * 
	 * @param storeCode 商户编号 store_code
	 */	
	public void setStoreCode (String storeCode) {
		this.storeCode = storeCode;
	}
	
	/**
	 * 
	 * @return 订单编号 order_code
	 */
	public String getOrderCode(){
		return orderCode;
	}
	
	/**
	 * 
	 * @param orderCode 订单编号 order_code
	 */	
	public void setOrderCode (String orderCode) {
		this.orderCode = orderCode;
	}
	
	/**
	 * 
	 * @return 用户编号 user_code
	 */
	public String getUserCode(){
		return userCode;
	}
	
	/**
	 * 
	 * @param userCode 用户编号 user_code
	 */	
	public void setUserCode (String userCode) {
		this.userCode = userCode;
	}
	
	/**
	 * 
	 * @return 订单类型 order_type
	 */
	public Integer getOrderType(){
		return orderType;
	}
	
	/**
	 * 
	 * @param orderType 订单类型 order_type
	 */	
	public void setOrderType (Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 
	 * @return 订单状态 order_status
	 */
	public Integer getOrderStatus(){
		return orderStatus;
	}
	
	/**
	 * 
	 * @param orderStatus 订单状态 order_status
	 */	
	public void setOrderStatus (Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 
	 * @return 订单客户备注 order_customer_memo
	 */
	public String getOrderCustomerMemo(){
		return orderCustomerMemo;
	}
	
	/**
	 * 
	 * @param orderCustomerMemo 订单客户备注 order_customer_memo
	 */	
	public void setOrderCustomerMemo (String orderCustomerMemo) {
		this.orderCustomerMemo = orderCustomerMemo;
	}
	
	/**
	 * 
	 * @return 订单商家备注 order_store_memo
	 */
	public String getOrderStoreMemo(){
		return orderStoreMemo;
	}
	
	/**
	 * 
	 * @param orderStoreMemo 订单商家备注 order_store_memo
	 */	
	public void setOrderStoreMemo (String orderStoreMemo) {
		this.orderStoreMemo = orderStoreMemo;
	}
	
	/**
	 * 
	 * @return 订单状态变更原因 order_status_change_memo
	 */
	public String getOrderStatusChangeMemo(){
		return orderStatusChangeMemo;
	}
	
	/**
	 * 
	 * @param orderStatusChangeMemo 订单状态变更原因 order_status_change_memo
	 */	
	public void setOrderStatusChangeMemo (String orderStatusChangeMemo) {
		this.orderStatusChangeMemo = orderStatusChangeMemo;
	}
	
	/**
	 * 
	 * @return 订单原价 order_original_price
	 */
	public BigDecimal getOrderOriginalPrice(){
		return orderOriginalPrice;
	}
	
	/**
	 * 
	 * @param orderOriginalPrice 订单原价 order_original_price
	 */	
	public void setOrderOriginalPrice (BigDecimal orderOriginalPrice) {
		this.orderOriginalPrice = orderOriginalPrice;
	}
	
	/**
	 * 
	 * @return 订单成交价 order_trans_price
	 */
	public BigDecimal getOrderTransPrice(){
		return orderTransPrice;
	}
	
	/**
	 * 
	 * @param orderTransPrice 订单成交价 order_trans_price
	 */	
	public void setOrderTransPrice (BigDecimal orderTransPrice) {
		this.orderTransPrice = orderTransPrice;
	}
	
	/**
	 * 
	 * @return 订单支付失效时间 order_pay_expire_time
	 */
	public Date getOrderPayExpireTime(){
		return orderPayExpireTime;
	}
	
	/**
	 * 
	 * @param orderPayExpireTime 订单支付失效时间 order_pay_expire_time
	 */	
	public void setOrderPayExpireTime (Date orderPayExpireTime) {
		this.orderPayExpireTime = orderPayExpireTime;
	}
	
	/**
	 * 
	 * @return 总运费 total_freight_charge
	 */
	public BigDecimal getTotalFreightCharge(){
		return totalFreightCharge;
	}
	
	/**
	 * 
	 * @param totalFreightCharge 总运费 total_freight_charge
	 */	
	public void setTotalFreightCharge (BigDecimal totalFreightCharge) {
		this.totalFreightCharge = totalFreightCharge;
	}
	
	/**
	 * 
	 * @return 币种 currency
	 */
	public Integer getCurrency(){
		return currency;
	}
	
	/**
	 * 
	 * @param currency 币种 currency
	 */	
	public void setCurrency (Integer currency) {
		this.currency = currency;
	}
	
	/**
	 * 
	 * @return 订单描述 order_desc
	 */
	public String getOrderDesc(){
		return orderDesc;
	}
	
	/**
	 * 
	 * @param orderDesc 订单描述 order_desc
	 */	
	public void setOrderDesc (String orderDesc) {
		this.orderDesc = orderDesc;
	}
	
	/**
	 * 
	 * @return 支付方式 pay_type
	 */
	public Integer getPayType(){
		return payType;
	}
	
	/**
	 * 
	 * @param payType 支付方式 pay_type
	 */	
	public void setPayType (Integer payType) {
		this.payType = payType;
	}
	
	/**
	 * 
	 * @return 订单版本号 order_version
	 */
	public Integer getOrderVersion(){
		return orderVersion;
	}
	
	/**
	 * 
	 * @param orderVersion 订单版本号 order_version
	 */	
	public void setOrderVersion (Integer orderVersion) {
		this.orderVersion = orderVersion;
	}
	
	/**
	 * 
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
	}
	
	/**
	 * 
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
