package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;




/**
 * 碎屏险手机型号产品的对应关系
 * The class is mapped to the table t_bas_bsi_phone_product_map 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiPhoneProductMapDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 产品ID bsi_product_id
	 */
	private Integer bsiProductId;
	
	/**
	 * 手机型号Id bsi_phone_model_id
	 */
	private Integer bsiPhoneModelId;
	
	/**
	 * 保险月数 bsi_insurance_period_month
	 */
	private Integer bsiInsurancePeriodMonth;
	
	
	/**
	 * 
	 * @return 产品ID bsi_product_id
	 */
	public Integer getBsiProductId(){
		return bsiProductId;
	}
	
	/**
	 * 
	 * @param bsiProductId 产品ID bsi_product_id
	 */	
	public void setBsiProductId (Integer bsiProductId) {
		this.bsiProductId = bsiProductId;
	}
	
	/**
	 * 
	 * @return 手机型号Id bsi_phone_model_id
	 */
	public Integer getBsiPhoneModelId(){
		return bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @param bsiPhoneModelId 手机型号Id bsi_phone_model_id
	 */	
	public void setBsiPhoneModelId (Integer bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @return 保险月数 bsi_insurance_period_month
	 */
	public Integer getBsiInsurancePeriodMonth(){
		return bsiInsurancePeriodMonth;
	}
	
	/**
	 * 
	 * @param bsiInsurancePeriodMonth 保险月数 bsi_insurance_period_month
	 */	
	public void setBsiInsurancePeriodMonth (Integer bsiInsurancePeriodMonth) {
		this.bsiInsurancePeriodMonth = bsiInsurancePeriodMonth;
	}
	


}
