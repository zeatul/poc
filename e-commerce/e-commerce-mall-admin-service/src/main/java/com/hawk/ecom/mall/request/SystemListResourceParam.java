package com.hawk.ecom.mall.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.pub.sql.PageParam;

public class SystemListResourceParam implements PageParam{


	
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

	public Integer getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(Integer pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	public String getParentNodeCode() {
		return parentNodeCode;
	}

	public void setParentNodeCode(String parentNodeCode) {
		this.parentNodeCode = parentNodeCode;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 页码
	 */
	private Integer pageIndex;
	
	/**
	 * 排序
	 */
	private String order;
	
	/**
	 * 每页数量
	 */
	private Integer pageRowCount;
	

	@NotEmpty("nodeCode")
	private String parentNodeCode;
	
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
	 * 节点状态 node_status
	 */
	private Integer nodeStatus;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

	

}
