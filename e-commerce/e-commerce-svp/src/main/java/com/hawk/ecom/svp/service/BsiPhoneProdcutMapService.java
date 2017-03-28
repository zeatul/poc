package com.hawk.ecom.svp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiPhoneProdcutMapDomain;
import com.hawk.ecom.svp.persist.mapper.BsiPhoneProdcutMapMapper;

@Service
public class BsiPhoneProdcutMapService {
	
	@Autowired
	private BsiPhoneProdcutMapMapper bsiPhoneProdcutMapMapper;
	
	public long queryProductId(long phoneModelId,int period){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bsiPhoneModelId", phoneModelId);
		params.put("bsiProductValidPeriod", period);
		List<BsiPhoneProdcutMapDomain> list = bsiPhoneProdcutMapMapper.loadDynamic(params);
		if (list.size() == 0)
			throw new RuntimeException("Couldn't found the product");
		
		return list.get(0).getBsiProductId();
	}

}
