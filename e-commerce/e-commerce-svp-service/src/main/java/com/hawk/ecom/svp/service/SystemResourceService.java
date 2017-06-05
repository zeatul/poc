package com.hawk.ecom.svp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domainex.SystemResourceExDomain;
import com.hawk.ecom.svp.persist.mapperex.SystemResourceExMapper;

@Service
public class SystemResourceService {

	@Autowired
	private SystemResourceExMapper systemResourceExMapper;
	
	public List<SystemResourceExDomain> h5main(){
		return systemResourceExMapper.h5main();
	}
}
