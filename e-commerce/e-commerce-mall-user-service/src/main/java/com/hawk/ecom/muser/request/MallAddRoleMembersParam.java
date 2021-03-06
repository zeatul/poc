package com.hawk.ecom.muser.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class MallAddRoleMembersParam {
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

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
	@NotEmpty("用户编号集合")
	private List<String> userCodes;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
	@NotEmpty
	@ValidWord
	private String roleCode;

}
