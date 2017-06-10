package com.hawk.ecom.mall.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MallMultiUserInfoResponse implements ResponseData{
	
	public MallMultiUserInfoResponse(List<MallUserInfoResponse> userInfos) {
		setUserInfos(userInfos);
	}


	public List<MallUserInfoResponse> getUserInfos() {
		return userInfos;
	}


	public void setUserInfos(List<MallUserInfoResponse> userInfos) {
		this.userInfos = userInfos;
		this.count = userInfos == null ? 0 : userInfos.size();
	}


	/**
	 * 返回记录数量
	 * 
	 * @return
	 */
	public Integer getCount() {
		return this.count;
	}

	private int count = 0;
	
	
	private List<MallUserInfoResponse> userInfos;
	
	
	

}
