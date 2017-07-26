package com.hawk.ecom.query.persist.domainex;

public class SystemResourceExDomain {
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getDepth() {
		return depth;
	}


	public void setDepth(Integer depth) {
		this.depth = depth;
	}


	public Integer getNodeStatus() {
		return nodeStatus;
	}


	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
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


	/**
	 * 节点名称 node_name
	 */
	private String nodeName;
	
		
	/**
	 * 节点值 node_value
	 */
	private String nodeValue;
	
	private Integer id;
	
	private Integer pid;
	
	private Integer depth;
	
	private Integer nodeStatus;
}
