package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: ssdata.dataservice.risk.alixiaohao.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-03-21 10:28:22
 */
public class SsdataDataserviceRiskAlixiaohaoQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 2561987329335552768L;

	/** 
	 * 是否阿里小号
	 */
	@ApiField("is_alixiaohao")
	private Boolean isAlixiaohao;

	public void setIsAlixiaohao(Boolean isAlixiaohao) {
		this.isAlixiaohao = isAlixiaohao;
	}
	public Boolean getIsAlixiaohao( ) {
		return this.isAlixiaohao;
	}

}
