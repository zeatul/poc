package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 碎屏险手机型号产品的对应关系
 * The class is mapped to the table t_svp_bsi_phone_prodcut_map 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiPhoneProdcutMapDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 产品ID bsi_product_id
	 */
	private Long bsiProductId;
	
	/**
	 * 手机型号Id bsi_phone_model_id
	 */
	private Long bsiPhoneModelId;
	
	
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
	 * @return 手机型号Id bsi_phone_model_id
	 */
	public Long getBsiPhoneModelId(){
		return bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @param bsiPhoneModelId 手机型号Id bsi_phone_model_id
	 */	
	public void setBsiPhoneModelId (Long bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}
	


}
