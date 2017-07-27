package com.hawk.ecom.base.persist.domainex;

public class BsiProductExDomain {
	
	public Integer getBsiProductId() {
		return bsiProductId;
	}

	public void setBsiProductId(Integer bsiProductId) {
		this.bsiProductId = bsiProductId;
	}

	public Integer getBsiInsurancePeriodMonth() {
		return bsiInsurancePeriodMonth;
	}

	public void setBsiInsurancePeriodMonth(Integer bsiInsurancePeriodMonth) {
		this.bsiInsurancePeriodMonth = bsiInsurancePeriodMonth;
	}

	public Integer getBsiGrade() {
		return bsiGrade;
	}

	public void setBsiGrade(Integer bsiGrade) {
		this.bsiGrade = bsiGrade;
	}

	/**
	 * 碎屏险产品ID bsi_product_id
	 */
	private Integer bsiProductId;
	
	
	
	/**
	 * 保险月数 bsi_insurance_period_month
	 */
	private Integer bsiInsurancePeriodMonth;
	
	/**
	 * 保险产品档次 bsi_grade
	 */
	private Integer bsiGrade;
	
	

}
