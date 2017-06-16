package com.hawk.ecom.muser.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import java.util.List;

public class MallRemoveUserParam {
	
	
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

	@NotEmpty
	private List<String> userCodes;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
}
