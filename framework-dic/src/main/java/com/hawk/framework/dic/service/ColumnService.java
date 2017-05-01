package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.ColumnDomain;
import com.hawk.framework.dic.persist.mapper.ColumnMapper;

@Service
public class ColumnService {

	@Autowired
	private ColumnMapper columnMapper;
	
	public void insertOrUpdate(ColumnDomain columnDomain){
		columnMapper.insert(columnDomain);
	}
}
