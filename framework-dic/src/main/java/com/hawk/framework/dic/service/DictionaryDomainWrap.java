package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.framework.dic.persist.domain.ApplicationDomain;
import com.hawk.framework.dic.persist.domain.ApplicationTableDomain;
import com.hawk.framework.dic.persist.domain.ColumnDomain;
import com.hawk.framework.dic.persist.domain.IndexColumnDomain;
import com.hawk.framework.dic.persist.domain.IndexDomain;
import com.hawk.framework.dic.persist.domain.TableDomain;
import com.hawk.framework.dic.persist.domain.WordDomain;

public class DictionaryDomainWrap {
	
	private Date createDate = new Date();
	private Date updateDate = createDate;
	
	
	
	

	private List<WordDomain> wordDomainList = new ArrayList<WordDomain>();	
	private List<ApplicationDomain> applicationDomainList = new ArrayList<ApplicationDomain>();
	private List<TableDomain> tableDomainList = new ArrayList<TableDomain>();
	private List<ColumnDomain> columnDomainList = new ArrayList<ColumnDomain>();
	private List<IndexDomain> indexDomainList = new ArrayList<IndexDomain>();
	private List<IndexColumnDomain> indexColumnDomainList = new ArrayList<IndexColumnDomain>();
	private List<ApplicationTableDomain> applicationTableDomainList = new ArrayList<ApplicationTableDomain>();
	
	private  Map<Class<?>,List<?>> map = new HashMap<Class<?>,List<?>>();
	
	public Map<Class<?>,List<?>> getMap(){
		return map;
	}
	
	public DictionaryDomainWrap(){
		map.put(WordDomain.class, wordDomainList);
		map.put(ApplicationDomain.class, applicationDomainList);
		map.put(TableDomain.class, tableDomainList);
		map.put(ColumnDomain.class, columnDomainList);
		map.put(IndexDomain.class, indexDomainList);
		map.put(IndexColumnDomain.class, indexColumnDomainList);
		map.put(ApplicationTableDomain.class, applicationTableDomainList);
	}

	public void add(WordDomain wordDomain) {
		wordDomainList.add(wordDomain);
		wordDomain.setCreateDate(createDate);
		wordDomain.setUpdateDate(updateDate);
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
