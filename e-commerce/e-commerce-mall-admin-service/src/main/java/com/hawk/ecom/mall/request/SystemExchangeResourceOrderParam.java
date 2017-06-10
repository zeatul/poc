package com.hawk.ecom.mall.request;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SystemExchangeResourceOrderParam {
	
	public String getNodeCodeA() {
		return nodeCodeA;
	}



	public void setNodeCodeA(String nodeCodeA) {
		this.nodeCodeA = nodeCodeA;
	}



	public String getNodeCodeB() {
		return nodeCodeB;
	}



	public void setNodeCodeB(String nodeCodeB) {
		this.nodeCodeB = nodeCodeB;
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
	@NotEmpty("nodeCode")
	private String nodeCodeA;
	
	/**
	 * 节点编号 node_code
	 */
	@NotEmpty("nodeCode")
	private String nodeCodeB;
	
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
