package com.hawk.ecom.picture.response;

import com.hawk.framework.pub.web.ResponseData;

public class UploadResponse implements ResponseData {
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	private Long size;

}
