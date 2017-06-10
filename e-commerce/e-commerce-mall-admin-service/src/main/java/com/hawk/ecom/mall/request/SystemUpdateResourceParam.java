package com.hawk.ecom.mall.request;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SystemUpdateResourceParam {


	public String getNodeCode() {
		return nodeCode;
	}



	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}



	public String getNodeName() {
		return nodeName;
	}



	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}



	public String getNodeValue() {
		return nodeValue;
	}



	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}



	public Integer getObjectOrder() {
		return objectOrder;
	}



	public void setObjectOrder(Integer objectOrder) {
		this.objectOrder = objectOrder;
	}



	public String getNodeDesc() {
		return nodeDesc;
	}



	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}



	public String getNodeIco() {
		return nodeIco;
	}



	public void setNodeIco(String nodeIco) {
		this.nodeIco = nodeIco;
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
	 * 节点名称 node_name
	 */	
	private String nodeName;

	/**
	 * 节点值 node_value
	 */	
	private String nodeValue;

	/**
	 * 节点序号 object_order
	 */
	private Integer objectOrder;
	

	/**
	 * 节点描述 node_desc
	 */
	private String nodeDesc;

	/**
	 * 节点图标 node_ico
	 */
	private String nodeIco;

	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
