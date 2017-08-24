package com.hawk.ecom.query.persist.mapperex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.query.persist.domainex.ProductCategoryExDomain;
import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;

public interface ProductExMapper {
	public List<ProductCategoryExDomain> listCategory();
	
	public List<ProductSkuExDomain> querySku(Map<String,Object> params);
	
	public int countSku(Map<String,Object> params);
	
	public ProductSkuExDomain loadSku(Map<String,Object> params);
	
	public ProductSkuExDomain loadSkuPriceAndQuantity(Map<String,Object> params);
	
	public Integer findAttrValueId(@Param("attrNameCode") String attrNameCode,@Param("attrValue") String attrValue);
	
	public Integer findAttrNameId(@Param("attrNameCode") String attrNameCode);
	
	/**
	 * 查找拥有特定属性值组合的产品SKU
	 * @param attrValueIds
	 * @param count
	 * @return
	 */
	public List<ProductSkuExDomain> findSkuByAttrValueIds(@Param("attrValueIds") List<Integer> attrValueIds,@Param("count") Integer count,
			@Param("skuStatus") Integer skuStatus,
			@Param("productStatus") Integer productStatus);
	
	/**
	 * 查找流量充值产品，输出结果包含流量大小
	 * @param attrValueIds
	 * @param count
	 * @param dataSizeAttrNameId
	 * @param regionTypeAttrNameId
	 * @return
	 */
	public List<ProductSkuExDomain> loadChargeDataProduct(@Param("attrValueIds") List<Integer> attrValueIds,
			@Param("count") Integer count,@Param("dataSizeAttrNameId") Integer dataSizeAttrNameId,
			@Param("regionTypeAttrNameId") Integer regionTypeAttrNameId,
			@Param("skuStatus") Integer skuStatus,
			@Param("productStatus") Integer productStatus);
}

