package com.hawk.ecom.pub.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiResponse<T> implements ResponseData{
	
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
		if (records ==  null){
			this.count = 0;
		}else{
			this.count = records.size();
		}
		
	}

	public int getCount() {
		return count;
	}

	

	private List<T> records;
	
	private int count = 0;
	
	public MultiResponse(List<T> records){
		setRecords(records);
	}

}
