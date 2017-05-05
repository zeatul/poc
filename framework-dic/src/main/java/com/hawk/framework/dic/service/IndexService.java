package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.IndexDomain;
import com.hawk.framework.dic.persist.mapper.IndexMapper;

@Service
public class IndexService {
	
	@Autowired
	private IndexMapper indexMapper;
	
	

	public void insertOrUpdate(IndexDomain indexDomain){
		indexMapper.insert(indexDomain);
	}
	
	
	
}
