package com.hawk.ecom.svp.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiBsiOrderDetailResponse implements ResponseData {
	
	public MultiBsiOrderDetailResponse(List<BsiOrderDetail> bsiOrderDetails){
		setBsiOrderDetails(bsiOrderDetails);
	}
	
	public List<BsiOrderDetail> getBsiOrderDetails() {
		return bsiOrderDetails;
	}

	public void setBsiOrderDetails(List<BsiOrderDetail> bsiOrderDetails) {
		this.bsiOrderDetails = bsiOrderDetails;
		this.count = bsiOrderDetails == null  ? 0 :bsiOrderDetails.size();
	}

	private List<BsiOrderDetail> bsiOrderDetails;
	
	/**
	 * 返回记录数量
	 * 
	 * @return
	 */
	public Integer getCount() {
		return this.count;
	}

	private int count = 0;
	
	public static class BsiOrderDetail{
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

		/**
		 * 任务号,与小宝对接用 bsi_task_code
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
	}

}
