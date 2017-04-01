package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;
import java.util.Date;




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
	 * 碎屏险订单id order_id
	 */
	private Long orderId;
	
	/**
	 * 手机型号ID bsi_phone_model_id
	 */
	private Integer bsiPhoneModelId;
	
	/**
	 * 碎屏险产品ID bsi_product_id
	 */
	private Integer bsiProductId;
	
	/**
	 * 设备唯一的串号 imei
	 */
	private String imei;
	
	/**
	 * 证件类型 bsi_benef_id_typ
	 */
	private Integer bsiBenefIdTyp;
	
	/**
	 * 证件号码 bsi_benef_id_number
	 */
	private String bsiBenefIdNumber;
	
	/**
	 * 投保者生日 bsi_benef_birthday
	 */
	private String bsiBenefBirthday;
	
	/**
	 * 投保者性别 bsi_benef_sex
	 */
	private Integer bsiBenefSex;
	
	/**
	 * 投保者姓名 bsi_benef_name
	 */
	private String bsiBenefName;
	
	/**
	 * 投保者手机号 bsi_benef_mobile_number
	 */
	private String bsiBenefMobileNumber;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
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
	 * @return 碎屏险订单id order_id
	 */
	public Long getOrderId(){
		return orderId;
	}
	
	/**
	 * 
	 * @param orderId 碎屏险订单id order_id
	 */	
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}
	
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
	 * @return 证件类型 bsi_benef_id_typ
	 */
	public Integer getBsiBenefIdTyp(){
		return bsiBenefIdTyp;
	}
	
	/**
	 * 
	 * @param bsiBenefIdTyp 证件类型 bsi_benef_id_typ
	 */	
	public void setBsiBenefIdTyp (Integer bsiBenefIdTyp) {
		this.bsiBenefIdTyp = bsiBenefIdTyp;
	}
	
	/**
	 * 
	 * @return 证件号码 bsi_benef_id_number
	 */
	public String getBsiBenefIdNumber(){
		return bsiBenefIdNumber;
	}
	
	/**
	 * 
	 * @param bsiBenefIdNumber 证件号码 bsi_benef_id_number
	 */	
	public void setBsiBenefIdNumber (String bsiBenefIdNumber) {
		this.bsiBenefIdNumber = bsiBenefIdNumber;
	}
	
	/**
	 * 
	 * @return 投保者生日 bsi_benef_birthday
	 */
	public String getBsiBenefBirthday(){
		return bsiBenefBirthday;
	}
	
	/**
	 * 
	 * @param bsiBenefBirthday 投保者生日 bsi_benef_birthday
	 */	
	public void setBsiBenefBirthday (String bsiBenefBirthday) {
		this.bsiBenefBirthday = bsiBenefBirthday;
	}
	
	/**
	 * 
	 * @return 投保者性别 bsi_benef_sex
	 */
	public Integer getBsiBenefSex(){
		return bsiBenefSex;
	}
	
	/**
	 * 
	 * @param bsiBenefSex 投保者性别 bsi_benef_sex
	 */	
	public void setBsiBenefSex (Integer bsiBenefSex) {
		this.bsiBenefSex = bsiBenefSex;
	}
	
	/**
	 * 
	 * @return 投保者姓名 bsi_benef_name
	 */
	public String getBsiBenefName(){
		return bsiBenefName;
	}
	
	/**
	 * 
	 * @param bsiBenefName 投保者姓名 bsi_benef_name
	 */	
	public void setBsiBenefName (String bsiBenefName) {
		this.bsiBenefName = bsiBenefName;
	}
	
	/**
	 * 
	 * @return 投保者手机号 bsi_benef_mobile_number
	 */
	public String getBsiBenefMobileNumber(){
		return bsiBenefMobileNumber;
	}
	
	/**
	 * 
	 * @param bsiBenefMobileNumber 投保者手机号 bsi_benef_mobile_number
	 */	
	public void setBsiBenefMobileNumber (String bsiBenefMobileNumber) {
		this.bsiBenefMobileNumber = bsiBenefMobileNumber;
	}
	
	/**
	 * 
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
