package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 碎屏险手机品牌型号
 * The class is mapped to the table t_svp_bsi_phone_model 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiPhoneModelDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 手机型号ID bsi_phone_model_id
	 */
	private Integer bsiPhoneModelId;
	
	/**
	 * 手机品牌 bsi_phone_brand
	 */
	private String bsiPhoneBrand;
	
	/**
	 * 手机型号 bsi_phone_model
	 */
	private String bsiPhoneModel;
	
	/**
	 * 型号状态 bsi_phone_model_status
	 */
	private Integer bsiPhoneModelStatus;
	
	
	/**
	 * 
	 * @return 手机型号ID bsi_phone_model_id
	 */
	public Integer getBsiPhoneModelId(){
		return bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @param bsiPhoneModelId 手机型号ID bsi_phone_model_id
	 */	
	public void setBsiPhoneModelId (Integer bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @return 手机品牌 bsi_phone_brand
	 */
	public String getBsiPhoneBrand(){
		return bsiPhoneBrand;
	}
	
	/**
	 * 
	 * @param bsiPhoneBrand 手机品牌 bsi_phone_brand
	 */	
	public void setBsiPhoneBrand (String bsiPhoneBrand) {
		this.bsiPhoneBrand = bsiPhoneBrand;
	}
	
	/**
	 * 
	 * @return 手机型号 bsi_phone_model
	 */
	public String getBsiPhoneModel(){
		return bsiPhoneModel;
	}
	
	/**
	 * 
	 * @param bsiPhoneModel 手机型号 bsi_phone_model
	 */	
	public void setBsiPhoneModel (String bsiPhoneModel) {
		this.bsiPhoneModel = bsiPhoneModel;
	}
	
	/**
	 * 
	 * @return 型号状态 bsi_phone_model_status
	 */
	public Integer getBsiPhoneModelStatus(){
		return bsiPhoneModelStatus;
	}
	
	/**
	 * 
	 * @param bsiPhoneModelStatus 型号状态 bsi_phone_model_status
	 */	
	public void setBsiPhoneModelStatus (Integer bsiPhoneModelStatus) {
		this.bsiPhoneModelStatus = bsiPhoneModelStatus;
	}
	


}
