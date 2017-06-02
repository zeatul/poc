package com.hawk.ecom.mall.request;

import java.util.List;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class SystemUpdateResourceStatusParam {


	public Integer getNodeStatus() {
		return nodeStatus;
	}


	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}


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
	 * 要改变成的资源状态
	 */
	@NotNull
	private Integer nodeStatus;
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
