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
	private Integer id;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 手机号码 mobile_number
	 */
	private String mobileNumber;
	
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
	 * 代金券失效日期 bsi_cash_coupon_invalid_date
	 */
	private Date bsiCashCouponInvalidDate;
	
	/**
	 * 代金券状态 bsi_cash_coupon_status
	 */
	private Integer bsiCashCouponStatus;
	
	/**
	 * 代金券类型 bsi_cash_coupon_type
	 */
	private String bsiCashCouponType;
	
	/**
	 * 代金券保险月份数 bsi_cash_coupon_period
	 */
	private Integer bsiCashCouponPeriod;
	
	/**
	 * 促销活动描述 promotion_activity_desc
	 */
	private String promotionActivityDesc;
	
	/**
	 * 促销活动编号
            
             promotion_activity_code
	 */
	private String promotionActivityCode;
	
	/**
	 * 代金券激活失败原因 bsi_cash_coupon_activate_error
	 */
	private String bsiCashCouponActivateError;
	
	/**
	 * 订单编号 order_code
	 */
	private String orderCode;
	
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
	public void setId (Integer  id) {
		this.id = id;
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
	 * @return 手机号码 mobile_number
	 */
	public String getMobileNumber(){
		return mobileNumber;
	}
	
	/**
	 * 
	 * @param mobileNumber 手机号码 mobile_number
	 */	
	public void setMobileNumber (String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	 * @return 代金券失效日期 bsi_cash_coupon_invalid_date
	 */
	public Date getBsiCashCouponInvalidDate(){
		return bsiCashCouponInvalidDate;
	}
	
	/**
	 * 
	 * @param bsiCashCouponInvalidDate 代金券失效日期 bsi_cash_coupon_invalid_date
	 */	
	public void setBsiCashCouponInvalidDate (Date bsiCashCouponInvalidDate) {
		this.bsiCashCouponInvalidDate = bsiCashCouponInvalidDate;
	}
	
	/**
	 * 
	 * @return 代金券状态 bsi_cash_coupon_status
	 */
	public Integer getBsiCashCouponStatus(){
		return bsiCashCouponStatus;
	}
	
	/**
	 * 
	 * @param bsiCashCouponStatus 代金券状态 bsi_cash_coupon_status
	 */	
	public void setBsiCashCouponStatus (Integer bsiCashCouponStatus) {
		this.bsiCashCouponStatus = bsiCashCouponStatus;
	}
	
	/**
	 * 
	 * @return 代金券类型 bsi_cash_coupon_type
	 */
	public String getBsiCashCouponType(){
		return bsiCashCouponType;
	}
	
	/**
	 * 
	 * @param bsiCashCouponType 代金券类型 bsi_cash_coupon_type
	 */	
	public void setBsiCashCouponType (String bsiCashCouponType) {
		this.bsiCashCouponType = bsiCashCouponType;
	}
	
	/**
	 * 
	 * @return 代金券保险月份数 bsi_cash_coupon_period
	 */
	public Integer getBsiCashCouponPeriod(){
		return bsiCashCouponPeriod;
	}
	
	/**
	 * 
	 * @param bsiCashCouponPeriod 代金券保险月份数 bsi_cash_coupon_period
	 */	
	public void setBsiCashCouponPeriod (Integer bsiCashCouponPeriod) {
		this.bsiCashCouponPeriod = bsiCashCouponPeriod;
	}
	
	/**
	 * 
	 * @return 促销活动描述 promotion_activity_desc
	 */
	public String getPromotionActivityDesc(){
		return promotionActivityDesc;
	}
	
	/**
	 * 
	 * @param promotionActivityDesc 促销活动描述 promotion_activity_desc
	 */	
	public void setPromotionActivityDesc (String promotionActivityDesc) {
		this.promotionActivityDesc = promotionActivityDesc;
	}
	
	/**
	 * 
	 * @return 促销活动编号
            
             promotion_activity_code
	 */
	public String getPromotionActivityCode(){
		return promotionActivityCode;
	}
	
	/**
	 * 
	 * @param promotionActivityCode 促销活动编号
            
             promotion_activity_code
	 */	
	public void setPromotionActivityCode (String promotionActivityCode) {
		this.promotionActivityCode = promotionActivityCode;
	}
	
	/**
	 * 
	 * @return 代金券激活失败原因 bsi_cash_coupon_activate_error
	 */
	public String getBsiCashCouponActivateError(){
		return bsiCashCouponActivateError;
	}
	
	/**
	 * 
	 * @param bsiCashCouponActivateError 代金券激活失败原因 bsi_cash_coupon_activate_error
	 */	
	public void setBsiCashCouponActivateError (String bsiCashCouponActivateError) {
		this.bsiCashCouponActivateError = bsiCashCouponActivateError;
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
