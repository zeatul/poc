package com.hawk.ecom.muser.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.pub.sql.PageParam;

public class BsiOrderDetailReportParam implements PageParam{
	
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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

	public Date getStdt() {
		return stdt;
	}

	public void setStdt(Date stdt) {
		this.stdt = stdt;
	}

	public Date getEndt() {
		return endt;
	}

	public void setEndt(Date endt) {
		this.endt = endt;
	}

	@NotNull("开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date stdt;
	
	@NotNull("结束日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endt;
	
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
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
