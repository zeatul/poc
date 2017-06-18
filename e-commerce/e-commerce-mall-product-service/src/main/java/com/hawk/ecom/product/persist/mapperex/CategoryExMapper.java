package com.hawk.ecom.product.persist.mapperex;

import org.apache.ibatis.annotations.Param;

public interface CategoryExMapper {
	public Integer maxObjectOrder(@Param("pid") Integer pid);
}
