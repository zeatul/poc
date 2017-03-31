package com.hawk.ecom.svp.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiPhoneModelResponse implements ResponseData {

	public MultiPhoneModelResponse(List<PhoneModel> phoneModels) {
		setPhoneModels(phoneModels);
	}

	public List<PhoneModel> getPhoneModels() {
		return phoneModels;
	}

	public void setPhoneModels(List<PhoneModel> phoneModels) {
		this.phoneModels = phoneModels;
		this.count = phoneModels == null ? 0 : phoneModels.size();
	}

	private List<PhoneModel> phoneModels;

	/**
	 * 返回记录数量
	 * 
	 * @return
	 */
	public Integer getCount() {
		return this.count;
	}

	private int count = 0;

	public static class PhoneModel {

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
}
