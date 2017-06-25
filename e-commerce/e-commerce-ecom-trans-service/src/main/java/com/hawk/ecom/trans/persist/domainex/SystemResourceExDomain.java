package com.hawk.ecom.trans.persist.domainex;

public class SystemResourceExDomain {
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


	/**
	 * 节点名称 node_name
	 */
	private String nodeName;
	
		
	/**
	 * 节点值 node_value
	 */
	private String nodeValue;
}
