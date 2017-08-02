package com.hawk.ecom.outer.service.chargeData;

public class ChargeDataConfigure {
	

	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getChargeUrl() {
		return chargeUrl;
	}
	public void setChargeUrl(String chargeUrl) {
		this.chargeUrl = chargeUrl;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	private  String apiKey = "tiexie";

	private  String securityKey = "hyzzyyz4p2";

	private String notifyUrl;
	
	private String chargeUrl = "http://load.flow.shziyuan.cn:8080/dwi/open-api/rest/recharge";
	private String queryUrl = "http://load.flow.shziyuan.cn:8001/open-api/rest/recharge/status";

}
