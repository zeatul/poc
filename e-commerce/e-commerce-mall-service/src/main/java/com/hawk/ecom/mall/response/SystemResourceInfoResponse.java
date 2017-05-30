package com.hawk.ecom.mall.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class SystemResourceInfoResponse implements ResponseData{
	
	
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




	public Integer getNodeStatus() {
		return nodeStatus;
	}




	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}




	public String getNodeDesc() {
		return nodeDesc;
	}




	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}




	public Date getCreateDate() {
		return createDate;
	}




	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}




	/**
	 * 节点编号 node_code
	 */
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
	 * 节点状态 node_status
	 */
	private Integer nodeStatus;
	
	/**
	 * 节点描述 node_desc
	 */
	private String nodeDesc;
	
	
	
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	
}
