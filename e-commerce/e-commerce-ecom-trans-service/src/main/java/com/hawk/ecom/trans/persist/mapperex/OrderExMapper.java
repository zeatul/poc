package com.hawk.ecom.trans.persist.mapperex;

import java.util.Map;

public interface OrderExMapper {
	
	public int closeUnpaiedOvertimeOrder(Map<String,Object> params);

}
