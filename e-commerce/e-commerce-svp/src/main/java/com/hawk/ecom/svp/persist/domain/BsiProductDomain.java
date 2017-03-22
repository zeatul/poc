package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 碎屏险产品
 * The class is mapped to the table t_svp_bsi_product 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiProductDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 产品ID bsi_product_id
	 */
	private Long bsiProductId;
	
	/**
	 * 产品名称 bsi_product_name
	 */
	private String bsiProductName;
	
	/**
	 * 有效期(月) bsi_produc_valid_period
	 */
	private Integer bsiProducValidPeriod;
	
	/**
	 * 产品状态 bsi_product_status
	 */
	private String bsiProductStatus;
	
	
	/**
	 * 
	 * @return 产品ID bsi_product_id
	 */
	public Long getBsiProductId(){
		return bsiProductId;
	}
	
	/**
	 * 
	 * @param bsiProductId 产品ID bsi_product_id
	 */	
	public void setBsiProductId (Long bsiProductId) {
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
	 * @return 有效期(月) bsi_produc_valid_period
	 */
	public Integer getBsiProducValidPeriod(){
		return bsiProducValidPeriod;
	}
	
	/**
	 * 
	 * @param bsiProducValidPeriod 有效期(月) bsi_produc_valid_period
	 */	
	public void setBsiProducValidPeriod (Integer bsiProducValidPeriod) {
		this.bsiProducValidPeriod = bsiProducValidPeriod;
	}
	
	/**
	 * 
	 * @return 产品状态 bsi_product_status
	 */
	public String getBsiProductStatus(){
		return bsiProductStatus;
	}
	
	/**
	 * 
	 * @param bsiProductStatus 产品状态 bsi_product_status
	 */	
	public void setBsiProductStatus (String bsiProductStatus) {
		this.bsiProductStatus = bsiProductStatus;
	}
	


}
