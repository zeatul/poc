package com.hawk.ecom.mall.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SystemRemoveResourceParam {


	public List<String> getNodeCodes() {
		return nodeCodes;
	}


	public void setNodeCodes(List<String> nodeCodes) {
		this.nodeCodes = nodeCodes;
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
	@NotEmpty
	private List<String> nodeCodes;
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
