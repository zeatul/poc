package com.hawk.ecom.mall.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class BsiStatOrderDetailParam {
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




	public String getOperatorCode() {
		return operatorCode;
	}




	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}




	@NotNull("开始日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date stdt;
	
	@NotNull("结束日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endt;
	
	
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
