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
	 * 订单原价 order_original_total_price
	 */
	private BigDecimal orderOriginalTotalPrice;
	
	/**
	 * 订单减免 order_deduction
	 */
	private BigDecimal orderDeduction;
	
	/**
	 * 订单实价 order_pay_total_price
	 */
	private BigDecimal orderPayTotalPrice;
	
	/**
	 * 订单支付失效时间 order_pay_expire_time
	 */
	private Date orderPayExpireTime;
	
	/**
	 * 运费 freight_charge
	 */
	private BigDecimal freightCharge;
	
	/**
	 * 订单描述 order_desc
	 */
	private String orderDesc;
	
	/**
	 * 支付方式 支付方式
	 */
	private Integer 支付方式;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
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
	 * @return 订单原价 order_original_total_price
	 */
	public BigDecimal getOrderOriginalTotalPrice(){
		return orderOriginalTotalPrice;
	}
	
	/**
	 * 
	 * @param orderOriginalTotalPrice 订单原价 order_original_total_price
	 */	
	public void setOrderOriginalTotalPrice (BigDecimal orderOriginalTotalPrice) {
		this.orderOriginalTotalPrice = orderOriginalTotalPrice;
	}
	
	/**
	 * 
	 * @return 订单减免 order_deduction
	 */
	public BigDecimal getOrderDeduction(){
		return orderDeduction;
	}
	
	/**
	 * 
	 * @param orderDeduction 订单减免 order_deduction
	 */	
	public void setOrderDeduction (BigDecimal orderDeduction) {
		this.orderDeduction = orderDeduction;
	}
	
	/**
	 * 
	 * @return 订单实价 order_pay_total_price
	 */
	public BigDecimal getOrderPayTotalPrice(){
		return orderPayTotalPrice;
	}
	
	/**
	 * 
	 * @param orderPayTotalPrice 订单实价 order_pay_total_price
	 */	
	public void setOrderPayTotalPrice (BigDecimal orderPayTotalPrice) {
		this.orderPayTotalPrice = orderPayTotalPrice;
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
	 * @return 运费 freight_charge
	 */
	public BigDecimal getFreightCharge(){
		return freightCharge;
	}
	
	/**
	 * 
	 * @param freightCharge 运费 freight_charge
	 */	
	public void setFreightCharge (BigDecimal freightCharge) {
		this.freightCharge = freightCharge;
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
	 * @return 支付方式 支付方式
	 */
	public Integer get支付方式(){
		return 支付方式;
	}
	
	/**
	 * 
	 * @param 支付方式 支付方式 支付方式
	 */	
	public void set支付方式 (Integer 支付方式) {
		this.支付方式 = 支付方式;
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
