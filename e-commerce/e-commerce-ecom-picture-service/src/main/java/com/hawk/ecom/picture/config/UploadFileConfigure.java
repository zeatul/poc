package com.hawk.ecom.picture.config;

import java.util.List;

public class UploadFileConfigure {
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<String> trackers) {
		this.trackers = trackers;
	}

	public long getMaxFilesSize() {
		return maxFilesSize;
	}

	public void setMaxFilesSize(long maxFilesSize) {
		this.maxFilesSize = maxFilesSize;
	}

	public long getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(long maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public int getFileSizeThreshold() {
		return fileSizeThreshold;
	}

	public void setFileSizeThreshold(int fileSizeThreshold) {
		this.fileSizeThreshold = fileSizeThreshold;
	}

	
	
	private List<String> trackers;
	
	/**
	 * 上传文件的临时路径
	 */
	private String location;
	
	/**
	 * 单个文件允许的最大字节数
	 */
	private long maxFilesSize;
	
	
	/**
	 * 单次请求允许的最大上传字节数
	 */
	private long maxRequestSize;
	
	
	/**
	 * 内存允许暂存的文件内容的最大字节数，为0：表示直接写磁盘
	 */
	private int fileSizeThreshold;

}
