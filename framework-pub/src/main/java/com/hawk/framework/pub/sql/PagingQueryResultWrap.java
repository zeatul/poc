package com.hawk.framework.pub.sql;

import java.util.ArrayList;
import java.util.List;

public class PagingQueryResultWrap<T> {
	
	

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getDbCount() {
		return dbCount;
	}

	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}

	/**
	 * 分页记录
	 */
	private List<T> records;
	
	/**
	 * 数据库记录总数
	 */
	private int dbCount ;
	
	public PagingQueryResultWrap(){
		records = new ArrayList<T>();
	}

}
