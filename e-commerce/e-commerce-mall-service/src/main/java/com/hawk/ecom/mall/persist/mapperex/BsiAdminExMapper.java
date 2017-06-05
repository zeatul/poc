package com.hawk.ecom.mall.persist.mapperex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BsiAdminExMapper {
	
	public List<HashMap<String,Object>> reportOrderDetail(Map<String,Object> params);
	
	public List<HashMap<String,Object>> statCoupon(Map<String,Object> params);
	
	public List<HashMap<String,Object>> statBsiOrderDetail(Map<String,Object> params);

}
