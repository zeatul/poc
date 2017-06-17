package com.hawk.ecom.product.request;

import java.util.Date;
import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class UpdateProductStatusParam {
	

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Date getOnSaleStdt() {
		return onSaleStdt;
	}

	public void setOnSaleStdt(Date onSaleStdt) {
		this.onSaleStdt = onSaleStdt;
	}

	public Date getOnSaleEndt() {
		return onSaleEndt;
	}

	public void setOnSaleEndt(Date onSaleEndt) {
		this.onSaleEndt = onSaleEndt;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@NotEmpty("产品主键集合")
	private List<Long> ids;
	
	@NotNull
	@ValidWord
	private Integer productStatus;
	
	/**
	 * 上架开始时间 on_sale_stdt
	 */
	private Date onSaleStdt;
	
	/**
	 * 上架结束时间 on_sale_endt
	 */
	private Date onSaleEndt;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
