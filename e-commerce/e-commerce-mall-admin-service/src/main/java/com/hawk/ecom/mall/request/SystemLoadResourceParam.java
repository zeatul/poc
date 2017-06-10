package com.hawk.ecom.mall.request;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SystemLoadResourceParam {
	
	public String getNodeCode() {
		return nodeCode;
	}




	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}




	public String getOperatorCode() {
		return operatorCode;
	}




	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}




	/**
	 * 节点编号 node_code
	 */
	@NotEmpty
	private String nodeCode;

	
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;


}
