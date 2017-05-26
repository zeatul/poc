package com.hawk.ecom.mall.request;

import java.util.List;

import com.hawk.ecom.mall.annotation.MallNotLogin;

public class MallRemoveAdminMembersParam {
	
	public List<String> getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(List<String> userCodes) {
		this.userCodes = userCodes;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 用户编号集合
	 */
	private List<String> userCodes;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
