package com.hawk.framework.dic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.IndexColumnDomain;
import com.hawk.framework.dic.persist.mapper.IndexColumnMapper;

@Service
public class IndexColumnService {
	
	@Autowired
	private IndexColumnMapper indexColumnMapper;
	
	public void insertOrUpdate(IndexColumnDomain indexColumnDomain){
		indexColumnMapper.insert(indexColumnDomain);
	}

}
