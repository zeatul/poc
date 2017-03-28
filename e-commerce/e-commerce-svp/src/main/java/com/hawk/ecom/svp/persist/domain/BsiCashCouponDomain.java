package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 代金券
 * The class is mapped to the table t_svp_bsi_cash_coupon 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiCashCouponDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 代金券编号 bsi_cash_coupon_code
	 */
	private String bsiCashCouponCode;
	
	/**
	 * 代金券名称 bsi_cash_coupon_name
	 */
	private String bsiCashCouponName;
	
	/**
	 * 代金券生成日期 bsi_cash_coupon_create_date
	 */
	private Date bsiCashCouponCreateDate;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 代金券所有者 bsi_cash_coupon_owner
	 */
	private String bsiCashCouponOwner;
	
	/**
	 * 代金券失效效日期 bsi_cash_coupon_invalid_date
	 */
	private Date bsiCashCouponInvalidDate;
	
	/**
	 * 代金券状态 bsi_cash_coupon_status
	 */
	private String bsiCashCouponStatus;
	
	/**
	 * 订单编号 order_number
	 */
	private String orderNumber;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 代金券编号 bsi_cash_coupon_code
	 */
	public String getBsiCashCouponCode(){
		return bsiCashCouponCode;
	}
	
	/**
	 * 
	 * @param bsiCashCouponCode 代金券编号 bsi_cash_coupon_code
	 */	
	public void setBsiCashCouponCode (String bsiCashCouponCode) {
		this.bsiCashCouponCode = bsiCashCouponCode;
	}
	
	/**
	 * 
	 * @return 代金券名称 bsi_cash_coupon_name
	 */
	public String getBsiCashCouponName(){
		return bsiCashCouponName;
	}
	
	/**
	 * 
	 * @param bsiCashCouponName 代金券名称 bsi_cash_coupon_name
	 */	
	public void setBsiCashCouponName (String bsiCashCouponName) {
		this.bsiCashCouponName = bsiCashCouponName;
	}
	
	/**
	 * 
	 * @return 代金券生成日期 bsi_cash_coupon_create_date
	 */
	public Date getBsiCashCouponCreateDate(){
		return bsiCashCouponCreateDate;
	}
	
	/**
	 * 
	 * @param bsiCashCouponCreateDate 代金券生成日期 bsi_cash_coupon_create_date
	 */	
	public void setBsiCashCouponCreateDate (Date bsiCashCouponCreateDate) {
		this.bsiCashCouponCreateDate = bsiCashCouponCreateDate;
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
	 * @return 代金券所有者 bsi_cash_coupon_owner
	 */
	public String getBsiCashCouponOwner(){
		return bsiCashCouponOwner;
	}
	
	/**
	 * 
	 * @param bsiCashCouponOwner 代金券所有者 bsi_cash_coupon_owner
	 */	
	public void setBsiCashCouponOwner (String bsiCashCouponOwner) {
		this.bsiCashCouponOwner = bsiCashCouponOwner;
	}
	
	/**
	 * 
	 * @return 代金券失效效日期 bsi_cash_coupon_invalid_date
	 */
	public Date getBsiCashCouponInvalidDate(){
		return bsiCashCouponInvalidDate;
	}
	
	/**
	 * 
	 * @param bsiCashCouponInvalidDate 代金券失效效日期 bsi_cash_coupon_invalid_date
	 */	
	public void setBsiCashCouponInvalidDate (Date bsiCashCouponInvalidDate) {
		this.bsiCashCouponInvalidDate = bsiCashCouponInvalidDate;
	}
	
	/**
	 * 
	 * @return 代金券状态 bsi_cash_coupon_status
	 */
	public String getBsiCashCouponStatus(){
		return bsiCashCouponStatus;
	}
	
	/**
	 * 
	 * @param bsiCashCouponStatus 代金券状态 bsi_cash_coupon_status
	 */	
	public void setBsiCashCouponStatus (String bsiCashCouponStatus) {
		this.bsiCashCouponStatus = bsiCashCouponStatus;
	}
	
	/**
	 * 
	 * @return 订单编号 order_number
	 */
	public String getOrderNumber(){
		return orderNumber;
	}
	
	/**
	 * 
	 * @param orderNumber 订单编号 order_number
	 */	
	public void setOrderNumber (String orderNumber) {
		this.orderNumber = orderNumber;
	}
	


}
