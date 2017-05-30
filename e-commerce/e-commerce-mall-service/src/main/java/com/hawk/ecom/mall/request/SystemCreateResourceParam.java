package com.hawk.ecom.mall.request;

import com.hawk.ecom.mall.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SystemCreateResourceParam {

	

	
	public Integer getNodeValueType() {
		return nodeValueType;
	}

	public void setNodeValueType(Integer nodeValueType) {
		this.nodeValueType = nodeValueType;
	}

	public Integer getObjectOrder() {
		return objectOrder;
	}

	public void setObjectOrder(Integer objectOrder) {
		this.objectOrder = objectOrder;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getParentNodeCode() {
		return parentNodeCode;
	}

	public void setParentNodeCode(String parentNodeCode) {
		this.parentNodeCode = parentNodeCode;
	}

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

	public Integer getNodeType() {
		return nodeType;
	}

	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	public Integer getNodeSubType() {
		return nodeSubType;
	}

	public void setNodeSubType(Integer nodeSubType) {
		this.nodeSubType = nodeSubType;
	}

	

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
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
	
	/**
	 * 父节点编号 为空 表示 根节点
	 */
	private String parentNodeCode;

	/**
	 * 节点编号 node_code
	 */
	private String nodeCode;

	/**
	 * 节点名称 node_name
	 */
	@NotEmpty
	private String nodeName;

	/**
	 * 节点类型 node_type
	 */
	private Integer nodeType;

	/**
	 * 节点子类型 node_sub_type
	 */
	private Integer nodeSubType;

	/**
	 * 节点值类型 node_value_type
	 */
	private Integer nodeValueType;

	/**
	 * 节点值 node_value
	 */
	@NotEmpty
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
