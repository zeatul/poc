package com.hawk.ecom.mall.request;

import java.util.List;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class MallUpdateUserStatusParam {



	public List<String> getUserCodes() {
		return userCodes;
	}


	public void setUserCodes(List<String> userCodes) {
		this.userCodes = userCodes;
	}


	public Integer getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	/**
	 * 要删除的节点编号集合
	 */
	@NotEmpty("用户编号集合")
	private List<String> userCodes;
	
	/**
	 * 要改变成的资源状态
	 */
	@NotNull
	private Integer userStatus;
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
