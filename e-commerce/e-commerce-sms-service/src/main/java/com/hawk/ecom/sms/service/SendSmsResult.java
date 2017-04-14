package com.hawk.ecom.sms.service;

public class SendSmsResult {
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	private String code;
	private String message;
	private String receipt;
	private boolean isSuccess = true;

}
