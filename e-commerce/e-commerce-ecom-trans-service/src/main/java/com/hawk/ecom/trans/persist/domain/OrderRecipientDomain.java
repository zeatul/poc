package com.hawk.ecom.trans.persist.domain;
import java.io.Serializable;




/**
 * 收货人信息
 * The class is mapped to the table t_tra_order_recipient 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderRecipientDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 订单主键 order_id
	 */
	private Integer orderId;
	
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
	 * 收件人姓名 recipient_name
	 */
	private String recipientName;
	
	/**
	 * 收件人手机号 recipient_mobilephone
	 */
	private String recipientMobilephone;
	
	/**
	 * 收件人座机号 recipient_telephone
	 */
	private String recipientTelephone;
	
	/**
	 * 收件人地址 recipient_address
	 */
	private String recipientAddress;
	
	/**
	 * 收件人地址邮编 recipient_post_code
	 */
	private String recipientPostCode;
	
	
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
	 * @return 订单主键 order_id
	 */
	public Integer getOrderId(){
		return orderId;
	}
	
	/**
	 * 
	 * @param orderId 订单主键 order_id
	 */	
	public void setOrderId (Integer orderId) {
		this.orderId = orderId;
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
	 * @return 收件人姓名 recipient_name
	 */
	public String getRecipientName(){
		return recipientName;
	}
	
	/**
	 * 
	 * @param recipientName 收件人姓名 recipient_name
	 */	
	public void setRecipientName (String recipientName) {
		this.recipientName = recipientName;
	}
	
	/**
	 * 
	 * @return 收件人手机号 recipient_mobilephone
	 */
	public String getRecipientMobilephone(){
		return recipientMobilephone;
	}
	
	/**
	 * 
	 * @param recipientMobilephone 收件人手机号 recipient_mobilephone
	 */	
	public void setRecipientMobilephone (String recipientMobilephone) {
		this.recipientMobilephone = recipientMobilephone;
	}
	
	/**
	 * 
	 * @return 收件人座机号 recipient_telephone
	 */
	public String getRecipientTelephone(){
		return recipientTelephone;
	}
	
	/**
	 * 
	 * @param recipientTelephone 收件人座机号 recipient_telephone
	 */	
	public void setRecipientTelephone (String recipientTelephone) {
		this.recipientTelephone = recipientTelephone;
	}
	
	/**
	 * 
	 * @return 收件人地址 recipient_address
	 */
	public String getRecipientAddress(){
		return recipientAddress;
	}
	
	/**
	 * 
	 * @param recipientAddress 收件人地址 recipient_address
	 */	
	public void setRecipientAddress (String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	
	/**
	 * 
	 * @return 收件人地址邮编 recipient_post_code
	 */
	public String getRecipientPostCode(){
		return recipientPostCode;
	}
	
	/**
	 * 
	 * @param recipientPostCode 收件人地址邮编 recipient_post_code
	 */	
	public void setRecipientPostCode (String recipientPostCode) {
		this.recipientPostCode = recipientPostCode;
	}
	


}
