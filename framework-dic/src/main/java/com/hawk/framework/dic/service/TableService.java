package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.TableDomain;
import com.hawk.framework.dic.persist.mapper.TableMapper;

@Service
public class TableService {
	
	@Autowired
	private TableMapper tableMapper;
	
	public void insertOrUpdate(TableDomain tableDomain){
		tableMapper.insert(tableDomain);
	}
	

}
