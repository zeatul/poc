package com.hawk.ecom.picture.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class UploadResponse implements ResponseData {
	
	
	
	public List<UploadFileInfo> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFileInfo> files) {
		this.files = files;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	private Long size;
	
	
	
	private List<UploadFileInfo> files;
	
	
	

}
