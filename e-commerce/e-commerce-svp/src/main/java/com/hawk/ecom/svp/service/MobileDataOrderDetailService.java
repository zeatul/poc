package com.hawk.ecom.svp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.MobileDataOrderDetailMapper;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MobileDataOrderDetailService {
	
	@Autowired
	private MobileDataOrderDetailMapper mobileDataOrderDetailMapper;
	
	public  MobileDataOrderDetailDomain loadByTaskId(String taskId){
		
		if (StringTools.isNullOrEmpty(taskId))
			throw new RuntimeException("任务编号为空");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("chargeTaskId", taskId);
		List<MobileDataOrderDetailDomain> list = mobileDataOrderDetailMapper.loadDynamic(params);
		
		if(list.size() == 0)
			return null;
		
		return list.get(0);
	}

}
