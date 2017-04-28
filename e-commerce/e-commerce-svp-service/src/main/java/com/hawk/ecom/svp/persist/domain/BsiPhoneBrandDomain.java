package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 
 * The class is mapped to the table t_svp_bsi_phone_brand 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiPhoneBrandDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 手机品牌 bsi_phone_brand
	 */
	private String bsiPhoneBrand;
	
	/**
	 * 拼首 spell_abbr
	 */
	private String spellAbbr;
	
	/**
	 * 序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 手机品牌状态 bsi_phone_brand_status
	 */
	private Integer bsiPhoneBrandStatus;
	
	
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
	 * @return 拼首 spell_abbr
	 */
	public String getSpellAbbr(){
		return spellAbbr;
	}
	
	/**
	 * 
	 * @param spellAbbr 拼首 spell_abbr
	 */	
	public void setSpellAbbr (String spellAbbr) {
		this.spellAbbr = spellAbbr;
	}
	
	/**
	 * 
	 * @return 序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
	}
	
	/**
	 * 
	 * @return 手机品牌状态 bsi_phone_brand_status
	 */
	public Integer getBsiPhoneBrandStatus(){
		return bsiPhoneBrandStatus;
	}
	
	/**
	 * 
	 * @param bsiPhoneBrandStatus 手机品牌状态 bsi_phone_brand_status
	 */	
	public void setBsiPhoneBrandStatus (Integer bsiPhoneBrandStatus) {
		this.bsiPhoneBrandStatus = bsiPhoneBrandStatus;
	}
	


}
