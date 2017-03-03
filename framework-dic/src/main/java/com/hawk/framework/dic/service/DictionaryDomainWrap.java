package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hawk.framework.dic.domain.ApplicationDomain;
import com.hawk.framework.dic.domain.ApplicationTableDomain;
import com.hawk.framework.dic.domain.ColumnDomain;
import com.hawk.framework.dic.domain.DataDefinitionDomain;
import com.hawk.framework.dic.domain.IndexColumnDomain;
import com.hawk.framework.dic.domain.IndexDomain;
import com.hawk.framework.dic.domain.TableDomain;

public class DictionaryDomainWrap {
	
	private Date createDate = new Date();
	private Date updateDate = createDate;
	
	
	
	public List<DataDefinitionDomain> getDataDefinitionDomainList() {
		return dataDefinitionDomainList;
	}

	public List<ApplicationDomain> getApplicationDomainList() {
		return applicationDomainList;
	}

	public List<TableDomain> getTableDomainList() {
		return tableDomainList;
	}

	public List<ColumnDomain> getColumnDomainList() {
		return columnDomainList;
	}

	public List<IndexDomain> getIndexDomainList() {
		return indexDomainList;
	}

	public List<IndexColumnDomain> getIndexColumnDomainList() {
		return indexColumnDomainList;
	}

	public List<ApplicationTableDomain> getApplicationTableDomainList() {
		return applicationTableDomainList;
	}
	
	

	private List<DataDefinitionDomain> dataDefinitionDomainList = new ArrayList<DataDefinitionDomain>();	
	private List<ApplicationDomain> applicationDomainList = new ArrayList<ApplicationDomain>();
	private List<TableDomain> tableDomainList = new ArrayList<TableDomain>();
	private List<ColumnDomain> columnDomainList = new ArrayList<ColumnDomain>();
	private List<IndexDomain> indexDomainList = new ArrayList<IndexDomain>();
	private List<IndexColumnDomain> indexColumnDomainList = new ArrayList<IndexColumnDomain>();
	private List<ApplicationTableDomain> applicationTableDomainList = new ArrayList<ApplicationTableDomain>();

	public void add(DataDefinitionDomain dataDefinitionDomain) {
		dataDefinitionDomainList.add(dataDefinitionDomain);
		dataDefinitionDomain.setCreateDate(createDate);
		dataDefinitionDomain.setUpdateDate(updateDate);
	}
	
	public void add(ApplicationDomain applicationDomain) {
		applicationDomainList.add(applicationDomain);
		applicationDomain.setCreateDate(createDate);
		applicationDomain.setUpdateDate(updateDate);
	}
	
	public void add(TableDomain tableDomain){
		tableDomainList.add(tableDomain);
		tableDomain.setCreateDate(createDate);
		tableDomain.setUpdateDate(updateDate);
	}
	
	public void add(ColumnDomain columnDomain){
		columnDomainList.add(columnDomain);
		columnDomain.setCreateDate(createDate);
		columnDomain.setUpdateDate(updateDate);
	}
	
	public void add(IndexDomain indexDomain){
		indexDomainList.add(indexDomain);
		indexDomain.setCreateDate(createDate);
		indexDomain.setUpdateDate(updateDate);
	}
	
	public void add(IndexColumnDomain indexColumnDomain){
		indexColumnDomainList.add(indexColumnDomain);
		indexColumnDomain.setCreateDate(createDate);
		indexColumnDomain.setUpdateDate(updateDate);
	}
	
	public void add(ApplicationTableDomain applicationTableDomain){
		applicationTableDomainList.add(applicationTableDomain);
		applicationTableDomain.setCreateDate(createDate);
		applicationTableDomain.setUpdateDate(updateDate);
	}
}
