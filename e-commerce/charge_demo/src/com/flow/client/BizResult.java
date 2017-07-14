package com.flow.client;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 前端调用结果
 * 
 * @author Administrator
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BizResult {
	private boolean succ = true;
	private String errorMsg;

	public boolean isSucc() {
		return succ;
	}

	public void setSucc(boolean succ) {
		this.succ = succ;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
