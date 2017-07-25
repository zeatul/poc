package com.hawk.ecom.product.persist.mapperex;
import java.util.List;
import java.util.Map;
import com.hawk.ecom.product.persist.domainex.AttrValueExDomain;

import org.apache.ibatis.annotations.Param;

/**
 * table = t_prd_attr_value
 * desc = 属性值表
 * 
 * @author Gen
 */
public interface AttrValueExMapper  {

	/**
	 * 根据主键加载记录
	 * @param id 主键
	 * @return 查询到的记录
	 */
	AttrValueExDomain load(@Param("id")Integer id );
	
	/**
	 * 动态条件加载记录
	 * @param params 查询条件
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<AttrValueExDomain> loadDynamic(Map<String,Object> params);
	
	/**
	 * 需要传入分页参数，请使用对应的工具类注入分页参数
	 * @param params
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<AttrValueExDomain> loadDynamicPaging(Map<String,Object> params);
	
	
	


}