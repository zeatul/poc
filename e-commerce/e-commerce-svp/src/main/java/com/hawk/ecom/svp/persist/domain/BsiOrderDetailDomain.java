package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 碎屏险订单明细
 * The class is mapped to the table t_svp_bsi_order_detail 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiOrderDetailDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 订单id svp_order_id
	 */
	private Long svpOrderId;
	
	/**
	 * 手机型号ID bsi_phone_model_id
	 */
	private Long bsiPhoneModelId;
	
	/**
	 * 碎屏险产品ID bsi_product_id
	 */
	private Long bsiProductId;
	
	/**
	 * 设备唯一的串号 imei
	 */
	private String imei;
	
	/**
	 * 证件类型 bsi_id_typ
	 */
	private Integer bsiIdTyp;
	
	/**
	 * 证件号码 bsi_id_number
	 */
	private String bsiIdNumber;
	
	/**
	 * 投保者生日 bsi_birthday
	 */
	private String bsiBirthday;
	
	/**
	 * 投保者性别 bsi_sex
	 */
	private String bsiSex;
	
	/**
	 * 投保者姓名 bsi_name
	 */
	private String bsiName;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 订单id svp_order_id
	 */
	public Long getSvpOrderId(){
		return svpOrderId;
	}
	
	/**
	 * 
	 * @param svpOrderId 订单id svp_order_id
	 */	
	public void setSvpOrderId (Long svpOrderId) {
		this.svpOrderId = svpOrderId;
	}
	
	/**
	 * 
	 * @return 手机型号ID bsi_phone_model_id
	 */
	public Long getBsiPhoneModelId(){
		return bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @param bsiPhoneModelId 手机型号ID bsi_phone_model_id
	 */	
	public void setBsiPhoneModelId (Long bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}
	
	/**
	 * 
	 * @return 碎屏险产品ID bsi_product_id
	 */
	public Long getBsiProductId(){
		return bsiProductId;
	}
	
	/**
	 * 
	 * @param bsiProductId 碎屏险产品ID bsi_product_id
	 */	
	public void setBsiProductId (Long bsiProductId) {
		this.bsiProductId = bsiProductId;
	}
	
	/**
	 * 
	 * @return 设备唯一的串号 imei
	 */
	public String getImei(){
		return imei;
	}
	
	/**
	 * 
	 * @param imei 设备唯一的串号 imei
	 */	
	public void setImei (String imei) {
		this.imei = imei;
	}
	
	/**
	 * 
	 * @return 证件类型 bsi_id_typ
	 */
	public Integer getBsiIdTyp(){
		return bsiIdTyp;
	}
	
	/**
	 * 
	 * @param bsiIdTyp 证件类型 bsi_id_typ
	 */	
	public void setBsiIdTyp (Integer bsiIdTyp) {
		this.bsiIdTyp = bsiIdTyp;
	}
	
	/**
	 * 
	 * @return 证件号码 bsi_id_number
	 */
	public String getBsiIdNumber(){
		return bsiIdNumber;
	}
	
	/**
	 * 
	 * @param bsiIdNumber 证件号码 bsi_id_number
	 */	
	public void setBsiIdNumber (String bsiIdNumber) {
		this.bsiIdNumber = bsiIdNumber;
	}
	
	/**
	 * 
	 * @return 投保者生日 bsi_birthday
	 */
	public String getBsiBirthday(){
		return bsiBirthday;
	}
	
	/**
	 * 
	 * @param bsiBirthday 投保者生日 bsi_birthday
	 */	
	public void setBsiBirthday (String bsiBirthday) {
		this.bsiBirthday = bsiBirthday;
	}
	
	/**
	 * 
	 * @return 投保者性别 bsi_sex
	 */
	public String getBsiSex(){
		return bsiSex;
	}
	
	/**
	 * 
	 * @param bsiSex 投保者性别 bsi_sex
	 */	
	public void setBsiSex (String bsiSex) {
		this.bsiSex = bsiSex;
	}
	
	/**
	 * 
	 * @return 投保者姓名 bsi_name
	 */
	public String getBsiName(){
		return bsiName;
	}
	
	/**
	 * 
	 * @param bsiName 投保者姓名 bsi_name
	 */	
	public void setBsiName (String bsiName) {
		this.bsiName = bsiName;
	}
	


}
