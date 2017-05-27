package com.hawk.ecom.mall.request;

public class SystemCreateResourceParam {

	

	
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

	public String getNodeValueType() {
		return nodeValueType;
	}

	public void setNodeValueType(String nodeValueType) {
		this.nodeValueType = nodeValueType;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	public String getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(String nodeOrder) {
		this.nodeOrder = nodeOrder;
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
	private String nodeValueType;

	/**
	 * 节点值 node_value
	 */
	private String nodeValue;

	/**
	 * 节点序号 node_order
	 */
	private String nodeOrder;

	

	/**
	 * 节点描述 node_desc
	 */
	private String nodeDesc;

	/**
	 * 节点图标 node_ico
	 */
	private String nodeIco;

	

}
