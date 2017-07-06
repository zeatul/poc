package com.hawk.ecom.outer.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hawk.framework.utility.tools.DateTools;

@Service
public class ChargeDataService {

	private final String API_KEY = "tiexie";

	private final String SECURITY_KEY = "hyzzyyz4p2";

	private final String NOTIFY_URL = "";

	public static class ChargeRequest {
		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public void setNotifyUrl(String notifyUrl) {
			this.notifyUrl = notifyUrl;
		}

		public String getCstmOrderNo() {
			return cstmOrderNo;
		}

		public void setCstmOrderNo(String cstmOrderNo) {
			this.cstmOrderNo = cstmOrderNo;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}


		private String apiKey;
		private String notifyUrl;
		private String cstmOrderNo;
		private String phone;
		private String productCode;
		private String sign;
		private String timeStamp;
	}

	private ChargeRequest buildChargeRequest(String mobileNumber,String productCode, String taskCode){
		ChargeRequest chargeRequest = new ChargeRequest();
		chargeRequest.setApiKey(API_KEY);
		chargeRequest.setCstmOrderNo(taskCode);
		chargeRequest.setNotifyUrl(taskCode);
		chargeRequest.setPhone(mobileNumber);
		chargeRequest.setProductCode(productCode);
		chargeRequest.setSign(sign);
		chargeRequest.setTimeStamp(DateTools.convert(new Date(), "yyyyMMddHHmmss"));
	}

	public void charge(String mobileNumber, String productCode, String taskCode) {

	}

}
