package com.hawk.ecom.svp.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class BsiOrderDetailResponse implements ResponseData {
	
	public Integer getBsiCashCouponStatus() {
		return bsiCashCouponStatus;
	}


	public void setBsiCashCouponStatus(Integer bsiCashCouponStatus) {
		this.bsiCashCouponStatus = bsiCashCouponStatus;
	}


	public String getBsiTaskCode() {
		return bsiTaskCode;
	}


	public void setBsiTaskCode(String bsiTaskCode) {
		this.bsiTaskCode = bsiTaskCode;
	}


	public Integer getBsiTaskStatus() {
		return bsiTaskStatus;
	}


	public void setBsiTaskStatus(Integer bsiTaskStatus) {
		this.bsiTaskStatus = bsiTaskStatus;
	}


	public String getBsiCashCouponCode() {
		return bsiCashCouponCode;
	}


	public void setBsiCashCouponCode(String bsiCashCouponCode) {
		this.bsiCashCouponCode = bsiCashCouponCode;
	}


	public String getBsiInsuranceCode() {
		return bsiInsuranceCode;
	}


	public void setBsiInsuranceCode(String bsiInsuranceCode) {
		this.bsiInsuranceCode = bsiInsuranceCode;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public String getBsiBenefName() {
		return bsiBenefName;
	}


	public void setBsiBenefName(String bsiBenefName) {
		this.bsiBenefName = bsiBenefName;
	}


	public String getBsiBenefMobileNumber() {
		return bsiBenefMobileNumber;
	}


	public void setBsiBenefMobileNumber(String bsiBenefMobileNumber) {
		this.bsiBenefMobileNumber = bsiBenefMobileNumber;
	}


	public Date getBsiCashCouponInvalidDate() {
		return bsiCashCouponInvalidDate;
	}


	public void setBsiCashCouponInvalidDate(Date bsiCashCouponInvalidDate) {
		this.bsiCashCouponInvalidDate = bsiCashCouponInvalidDate;
	}


	/**
	 * 任务号,小宝外部订单号
	 */
	private String bsiTaskCode;
	
	/**
	 * 任务状态 bsi_task_status
	 */
	private Integer bsiTaskStatus;
	
	/**
	 * 代金券编号 bsi_cash_coupon_code
	 */
	private String bsiCashCouponCode;
	
	/**
	 * 小宝订单编号 bsi_insurance_code
	 */
	private String bsiInsuranceCode;
	
	/**
	 * 代金券的所有者手机号
	 */
	private String mobileNumber;
	
	/**
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	

	/**
	 * 设备唯一的串号 imei
	 */
	private String imei;
	
	
	/**
	 * 投保者姓名 bsi_benef_name
	 */
	private String bsiBenefName;
	
	/**
	 * 投保者手机号 bsi_benef_mobile_number
	 */
	private String bsiBenefMobileNumber;
	
	
	/**
	 * 代金券失效日期
	 */
	private Date bsiCashCouponInvalidDate;
	
	/**
	 * 代金券状态 bsi_cash_coupon_status
	 */
	private Integer bsiCashCouponStatus;

}
