package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.ApplicationDomain;
import com.hawk.framework.dic.persist.mapper.ApplicationMapper;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;
	
	public void insertOrUpdate(ApplicationDomain applicationDomain){
		applicationMapper.insert(applicationDomain);
	}
}
