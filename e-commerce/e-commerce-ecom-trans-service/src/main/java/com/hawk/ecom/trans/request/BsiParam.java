package com.hawk.ecom.trans.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class BsiParam {
	
	
	


	public Integer getOuterPhoneModelId() {
		return outerPhoneModelId;
	}

	public void setOuterPhoneModelId(Integer outerPhoneModelId) {
		this.outerPhoneModelId = outerPhoneModelId;
	}

	public Integer getOuterProductId() {
		return outerProductId;
	}

	public void setOuterProductId(Integer outerProductId) {
		this.outerProductId = outerProductId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getBenefIdTyp() {
		return benefIdTyp;
	}

	public void setBenefIdTyp(Integer benefIdTyp) {
		this.benefIdTyp = benefIdTyp;
	}

	public String getBenefIdNumber() {
		return benefIdNumber;
	}

	public void setBenefIdNumber(String benefIdNumber) {
		this.benefIdNumber = benefIdNumber;
	}

	public String getBenefName() {
		return benefName;
	}

	public void setBenefName(String benefName) {
		this.benefName = benefName;
	}

	public String getBenefMobileNumber() {
		return benefMobileNumber;
	}

	public void setBenefMobileNumber(String benefMobileNumber) {
		this.benefMobileNumber = benefMobileNumber;
	}

	/**
	 * 手机型号ID outer_phone_model_id
	 */
	@NotNull
	private Integer outerPhoneModelId;
	
	/**
	 * 供应商产品ID outer_product_id
	 */
	@NotNull
	private Integer outerProductId;
	
	/**
	 * 设备唯一的串号 imei
	 */
	@NotEmpty
	private String imei;
	
	/**
	 * 受益人证件类型 benef_id_typ
	 */
	@NotNull
	private Integer benefIdTyp;
	
	/**
	 * 受益人证件号码 benef_id_number
	 */
	@NotEmpty
	private String benefIdNumber;
	
	
	
	/**
	 * 受益人姓名 benef_name
	 */
	@NotEmpty
	private String benefName;
	
	/**
	 * 受益人手机号 benef_mobile_number
	 */
	@NotEmpty
	private String benefMobileNumber;
	


	
	
	

	
	

}
