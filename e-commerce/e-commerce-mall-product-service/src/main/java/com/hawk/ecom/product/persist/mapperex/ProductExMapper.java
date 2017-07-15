package com.hawk.ecom.product.persist.mapperex;

import org.apache.ibatis.annotations.Param;

public interface ProductExMapper {
	
	
	public Integer orderDetailCount(@Param("skuId") Integer skuId);

}
