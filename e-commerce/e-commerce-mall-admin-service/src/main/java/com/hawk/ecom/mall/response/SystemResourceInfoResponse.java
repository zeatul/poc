package com.hawk.ecom.mall.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class SystemResourceInfoResponse implements ResponseData{
	
	
	public Date getUpdateDate() {
		return updateDate;
	}




	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




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




	public Integer getNodeValueType() {
		return nodeValueType;
	}




	public void setNodeValueType(Integer nodeValueType) {
		this.nodeValueType = nodeValueType;
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
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date;
	 */
	private Date updateDate;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 父ID pid
	 */
	private Integer pid;
	
}
