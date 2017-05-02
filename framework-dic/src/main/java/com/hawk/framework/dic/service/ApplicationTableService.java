package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.ApplicationTableDomain;
import com.hawk.framework.dic.persist.mapper.ApplicationTableMapper;

@Service
public class ApplicationTableService {

	@Autowired
	public ApplicationTableMapper applicationTableMapper;
	
	public void insertOrUpdate(ApplicationTableDomain applicationTableDomain){
		applicationTableMapper.insert(applicationTableDomain);
	}
}
