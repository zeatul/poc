package com.hawk.ecom.query.response;

public class PhoneModel {
	public Integer getBsiPhoneModelId() {
		return bsiPhoneModelId;
	}

	public void setBsiPhoneModelId(Integer bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}

	public String getBsiPhoneBrand() {
		return bsiPhoneBrand;
	}

	public void setBsiPhoneBrand(String bsiPhoneBrand) {
		this.bsiPhoneBrand = bsiPhoneBrand;
	}

	public String getBsiPhoneModel() {
		return bsiPhoneModel;
	}

	public void setBsiPhoneModel(String bsiPhoneModel) {
		this.bsiPhoneModel = bsiPhoneModel;
	}

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
}
