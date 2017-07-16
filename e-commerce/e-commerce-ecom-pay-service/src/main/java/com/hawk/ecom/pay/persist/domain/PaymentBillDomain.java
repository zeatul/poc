package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 支付单
 * The class is mapped to the table t_pay_payment_bill 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PaymentBillDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 订单描述 order_desc
	 */
	private String orderDesc;
	
	/**
	 * 订单内容 order_body
	 */
	private String orderBody;
	
	/**
	 * 应用编号 application_code
	 */
	private String applicationCode;
	
	/**
	 * 支付单编号 payment_bill_code
	 */
	private String paymentBillCode;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 订单支付总价 total_amount
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * 支付单状态 payment_bill_status
	 */
	private Integer paymentBillStatus;
	
	/**
	 * 支付分类编号 payment_category_code
	 */
	private String paymentCategoryCode;
	
	/**
	 * 支付单备注 payment_bill_memo
	 */
	private String paymentBillMemo;
	
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
	 * @return 订单内容 order_body
	 */
	public String getOrderBody(){
		return orderBody;
	}
	
	/**
	 * 
	 * @param orderBody 订单内容 order_body
	 */	
	public void setOrderBody (String orderBody) {
		this.orderBody = orderBody;
	}
	
	/**
	 * 
	 * @return 应用编号 application_code
	 */
	public String getApplicationCode(){
		return applicationCode;
	}
	
	/**
	 * 
	 * @param applicationCode 应用编号 application_code
	 */	
	public void setApplicationCode (String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	/**
	 * 
	 * @return 支付单编号 payment_bill_code
	 */
	public String getPaymentBillCode(){
		return paymentBillCode;
	}
	
	/**
	 * 
	 * @param paymentBillCode 支付单编号 payment_bill_code
	 */	
	public void setPaymentBillCode (String paymentBillCode) {
		this.paymentBillCode = paymentBillCode;
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
	 * @return 订单支付总价 total_amount
	 */
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}
	
	/**
	 * 
	 * @param totalAmount 订单支付总价 total_amount
	 */	
	public void setTotalAmount (BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
	 * @return 支付单状态 payment_bill_status
	 */
	public Integer getPaymentBillStatus(){
		return paymentBillStatus;
	}
	
	/**
	 * 
	 * @param paymentBillStatus 支付单状态 payment_bill_status
	 */	
	public void setPaymentBillStatus (Integer paymentBillStatus) {
		this.paymentBillStatus = paymentBillStatus;
	}
	
	/**
	 * 
	 * @return 支付分类编号 payment_category_code
	 */
	public String getPaymentCategoryCode(){
		return paymentCategoryCode;
	}
	
	/**
	 * 
	 * @param paymentCategoryCode 支付分类编号 payment_category_code
	 */	
	public void setPaymentCategoryCode (String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}
	
	/**
	 * 
	 * @return 支付单备注 payment_bill_memo
	 */
	public String getPaymentBillMemo(){
		return paymentBillMemo;
	}
	
	/**
	 * 
	 * @param paymentBillMemo 支付单备注 payment_bill_memo
	 */	
	public void setPaymentBillMemo (String paymentBillMemo) {
		this.paymentBillMemo = paymentBillMemo;
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
