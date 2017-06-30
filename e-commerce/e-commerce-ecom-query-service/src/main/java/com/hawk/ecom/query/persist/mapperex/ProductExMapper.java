package com.hawk.ecom.query.persist.mapperex;

import java.util.List;
import java.util.Map;

import com.hawk.ecom.query.persist.domainex.ProductCategoryExDomain;
import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;

public interface ProductExMapper {
	public List<ProductCategoryExDomain> listCategory();
	
	public List<ProductSkuExDomain> querySku(Map<String,Object> params);
	
	public int countSku(Map<String,Object> params);
	
	public ProductSkuExDomain loadSku(Map<String,Object> params);
	
	public ProductSkuExDomain loadSkuPriceAndQuantity(Map<String,Object> params);
	
}

