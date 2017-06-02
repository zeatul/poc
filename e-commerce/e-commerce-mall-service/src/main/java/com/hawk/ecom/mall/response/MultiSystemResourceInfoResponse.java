package com.hawk.ecom.mall.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiSystemResourceInfoResponse implements ResponseData{
	public List<SystemResourceInfoResponse> getSystemResources() {
		return systemResources;
	}


	public void setSystemResources(List<SystemResourceInfoResponse> systemResources) {
		this.systemResources = systemResources;
		this.count = systemResources == null ? 0 : systemResources.size();
	}


	public MultiSystemResourceInfoResponse(List<SystemResourceInfoResponse> systemResources) {
		setSystemResources(systemResources);
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
	private List<SystemResourceInfoResponse> systemResources;
}
