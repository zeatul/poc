package com.hawk.ecom.mall.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.persist.mapperex.BsiAdminExMapper;
import com.hawk.ecom.mall.request.BsiOrderDetailReportParam;

@Service
public class BsiAdminService {
	
	@Autowired
	private BsiAdminExMapper bsiAdminExMapper;
	
	public List<HashMap<String,Object>> reportOrderDetail(BsiOrderDetailReportParam bsiOrderDetailReportParam){
		return bsiAdminExMapper.reportOrderDetail(bsiOrderDetailReportParam.getStdt(),bsiOrderDetailReportParam.getEndt());
	}

}
