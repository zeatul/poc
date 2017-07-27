package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;




/**
 * 碎屏险产品
 * The class is mapped to the table t_bas_bsi_product 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiProductDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 碎屏险产品ID bsi_product_id
	 */
	private Integer bsiProductId;
	
	/**
	 * 产品名称 bsi_product_name
	 */
	private String bsiProductName;
	
	/**
	 * 保险月数 bsi_insurance_period_month
	 */
	private Integer bsiInsurancePeriodMonth;
	
	/**
	 * 保险产品档次 bsi_grade
	 */
	private Integer bsiGrade;
	
	/**
	 * 产品状态 bsi_product_status
	 */
	private Integer bsiProductStatus;
	
	/**
	 * 显示价格 bsi_display_price
	 */
	private BigDecimal bsiDisplayPrice;
	
	/**
	 * 批发价格 bsi_trade_price
	 */
	private BigDecimal bsiTradePrice;
	
	/**
	 * 销售价格 bsi_retail_price
	 */
	private BigDecimal bsiRetailPrice;
	
	
	/**
	 * 
	 * @return 碎屏险产品ID bsi_product_id
	 */
	public Integer getBsiProductId(){
		return bsiProductId;
	}
	
	/**
	 * 
	 * @param bsiProductId 碎屏险产品ID bsi_product_id
	 */	
	public void setBsiProductId (Integer bsiProductId) {
		this.bsiProductId = bsiProductId;
	}
	
	/**
	 * 
	 * @return 产品名称 bsi_product_name
	 */
	public String getBsiProductName(){
		return bsiProductName;
	}
	
	/**
	 * 
	 * @param bsiProductName 产品名称 bsi_product_name
	 */	
	public void setBsiProductName (String bsiProductName) {
		this.bsiProductName = bsiProductName;
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
	
	/**
	 * 
	 * @return 保险产品档次 bsi_grade
	 */
	public Integer getBsiGrade(){
		return bsiGrade;
	}
	
	/**
	 * 
	 * @param bsiGrade 保险产品档次 bsi_grade
	 */	
	public void setBsiGrade (Integer bsiGrade) {
		this.bsiGrade = bsiGrade;
	}
	
	/**
	 * 
	 * @return 产品状态 bsi_product_status
	 */
	public Integer getBsiProductStatus(){
		return bsiProductStatus;
	}
	
	/**
	 * 
	 * @param bsiProductStatus 产品状态 bsi_product_status
	 */	
	public void setBsiProductStatus (Integer bsiProductStatus) {
		this.bsiProductStatus = bsiProductStatus;
	}
	
	/**
	 * 
	 * @return 显示价格 bsi_display_price
	 */
	public BigDecimal getBsiDisplayPrice(){
		return bsiDisplayPrice;
	}
	
	/**
	 * 
	 * @param bsiDisplayPrice 显示价格 bsi_display_price
	 */	
	public void setBsiDisplayPrice (BigDecimal bsiDisplayPrice) {
		this.bsiDisplayPrice = bsiDisplayPrice;
	}
	
	/**
	 * 
	 * @return 批发价格 bsi_trade_price
	 */
	public BigDecimal getBsiTradePrice(){
		return bsiTradePrice;
	}
	
	/**
	 * 
	 * @param bsiTradePrice 批发价格 bsi_trade_price
	 */	
	public void setBsiTradePrice (BigDecimal bsiTradePrice) {
		this.bsiTradePrice = bsiTradePrice;
	}
	
	/**
	 * 
	 * @return 销售价格 bsi_retail_price
	 */
	public BigDecimal getBsiRetailPrice(){
		return bsiRetailPrice;
	}
	
	/**
	 * 
	 * @param bsiRetailPrice 销售价格 bsi_retail_price
	 */	
	public void setBsiRetailPrice (BigDecimal bsiRetailPrice) {
		this.bsiRetailPrice = bsiRetailPrice;
	}
	


}
