package com.hawk.ecom.pub.response;

import java.util.List;

import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.ResponseData;

public class MultiResponse<T> implements ResponseData{
	
	

	public Integer getDbcount() {
		return dbcount;
	}

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

	public Integer getCount() {
		return count;
	}

	

	private List<T> records;
	
	private Integer count ;
	
	private Integer dbcount ;
	
	public MultiResponse(List<T> records){
		setRecords(records);
	}
	
	public MultiResponse(List<T> records,int dbCount){
		setRecords(records);
		this.dbcount = dbCount;
	}
	
	public MultiResponse(PagingQueryResultWrap<T> wrap ){
		setRecords(wrap.getRecords());
		this.dbcount = wrap.getDbCount();
	}

}
