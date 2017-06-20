package com.hawk.ecom.mall.persist.mapperex;

import org.apache.ibatis.annotations.Param;

public interface SystemResourceExMapper {

	public Integer maxObjectOrder(@Param("pid") Integer pid);
}
