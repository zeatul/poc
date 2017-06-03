package com.hawk.ecom.mall.persist.mapperex;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BsiAdminExMapper {
	
	public List<HashMap<String,Object>> reportOrderDetail(@Param("stdt") Date stdt ,@Param("endt") Date endt);

}
