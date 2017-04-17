package com.hawk.ecom.svp.response;

import java.util.Date;
import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiCouponResponse implements ResponseData{
	
	public MultiCouponResponse(List<CashCoupon> cashCoupons){
		setCashCoupons(cashCoupons);
	}
	
	public List<CashCoupon> getCashCoupons() {
		return cashCoupons;
	}


	public void setCashCoupons(List<CashCoupon> cashCoupons) {
		this.cashCoupons = cashCoupons;
		this.count = cashCoupons == null  ? 0 :cashCoupons.size();
	}

	private List<CashCoupon> cashCoupons;
	
	/**
	 * 返回记录数量
	 * 
	 * @return
	 */
	public Integer getCount() {
		return this.count;
	}

	private int count = 0;
	
	
	public static class CashCoupon{
	public String getBsiCashCouponCode() {
		return bsiCashCouponCode;
	}



	public void setBsiCashCouponCode(String bsiCashCouponCode) {
		this.bsiCashCouponCode = bsiCashCouponCode;
	}



	public String getBsiCashCouponName() {
		return bsiCashCouponName;
	}



	public void setBsiCashCouponName(String bsiCashCouponName) {
		this.bsiCashCouponName = bsiCashCouponName;
	}



	public Date getBsiCashCouponCreateDate() {
		return bsiCashCouponCreateDate;
	}



	public void setBsiCashCouponCreateDate(Date bsiCashCouponCreateDate) {
		this.bsiCashCouponCreateDate = bsiCashCouponCreateDate;
	}



	public Date getBsiCashCouponInvalidDate() {
		return bsiCashCouponInvalidDate;
	}



	public void setBsiCashCouponInvalidDate(Date bsiCashCouponInvalidDate) {
		this.bsiCashCouponInvalidDate = bsiCashCouponInvalidDate;
	}



	public Integer getBsiCashCouponStatus() {
		return bsiCashCouponStatus;
	}



	public void setBsiCashCouponStatus(Integer bsiCashCouponStatus) {
		this.bsiCashCouponStatus = bsiCashCouponStatus;
	}



	public Integer getBsiCashCouponPeriod() {
		return bsiCashCouponPeriod;
	}



	public void setBsiCashCouponPeriod(Integer bsiCashCouponPeriod) {
		this.bsiCashCouponPeriod = bsiCashCouponPeriod;
	}



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
	 * 代金券保险月份数 bsi_cash_coupon_period
	 */
	private Integer bsiCashCouponPeriod;
	
	}

}
