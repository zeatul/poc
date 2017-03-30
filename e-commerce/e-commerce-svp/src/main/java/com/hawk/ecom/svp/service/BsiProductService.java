package com.hawk.ecom.svp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.persist.mapper.BsiProductMapper;

@Service
public class BsiProductService {
	
	@Autowired
	private BsiProductMapper bsiProductMapper;
	
	public BsiProductDomain queryProduct(int productId){
		BsiProductDomain bsiProductDomain = bsiProductMapper.load(productId);
		if (bsiProductDomain == null)
			throw new RuntimeException("Couldn't find the product");
		return bsiProductDomain;
	}

}
