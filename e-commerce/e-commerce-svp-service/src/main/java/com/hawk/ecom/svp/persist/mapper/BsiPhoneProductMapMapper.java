package com.hawk.ecom.svp.persist.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.ecom.svp.persist.domain.BsiPhoneProductMapDomain;
import org.apache.ibatis.annotations.Param;

/**
 * table = t_svp_bsi_phone_product_map
 * desc = 碎屏险手机型号产品的对应关系
 * 
 * @author Gen
 */
public interface BsiPhoneProductMapMapper  {

	/**
	 * 根据主键加载记录
	 * @param bsiProductId 产品ID
	 * @param bsiPhoneModelId 手机型号Id
	 * @return 查询到的记录
	 */
	BsiPhoneProductMapDomain load(@Param("bsiProductId")Integer bsiProductId, @Param("bsiPhoneModelId")Integer bsiPhoneModelId );
	
	/**
	 * 动态条件加载记录
	 * @param params 查询条件
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<BsiPhoneProductMapDomain> loadDynamic(Map<String,Object> params);
	
	/**
	 * 需要传入分页参数，请使用对应的工具类注入分页参数
	 * @param params
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<BsiPhoneProductMapDomain> loadDynamicPaging(Map<String,Object> params);
	
	/**
	 * 计算符合条件的记录数
	 * @param params
	 * @return 符合条件的记录数
	 */
	int count(Map<String,Object> params);
	
	/**
	 * 根据主键查询记录数,用来判断主键对应的记录是否存在
	 * @param bsiProductId 产品ID
	 * @param bsiPhoneModelId 手机型号Id
	 * @return 查询到的记录数，0：表示记录不存在，1：表示记录存在
	 */
	int countByPK(@Param("bsiProductId")Integer bsiProductId, @Param("bsiPhoneModelId")Integer bsiPhoneModelId );
	
	
	/**
	 * 插入记录
	 * @param BsiPhoneProductMapDomain 碎屏险手机型号产品的对应关系
	 * @return 插入的记录数
	 */
	int insert(BsiPhoneProductMapDomain bsiPhoneProductMapDomain);
	
	/**
	 * 根据主键删除记录
	 * @param bsiProductId 产品ID
	 * @param bsiPhoneModelId 手机型号Id
	 * @return 删除的记录数
	 */
	int delete(@Param("bsiProductId")Integer bsiProductId, @Param("bsiPhoneModelId")Integer bsiPhoneModelId );
	
	/**
	 * 动态删除记录
	 * @param params 删除条件参数集合
	 * @return 删除的记录数
	 */
	int deleteDynamic(Map<String,Object> params);
	
	/**
	 * 更新,全字段更新,空值被更新成null
	 * @param BsiPhoneProductMapDomain 碎屏险手机型号产品的对应关系
	 * @return 更新的记录数
	 */
	int update(BsiPhoneProductMapDomain bsiPhoneProductMapDomain);
	
	/**
	 * 更新,只更新不为空的值，适合根据主键更新特定字段
	 * @param BsiPhoneProductMapDomain 碎屏险手机型号产品的对应关系
	 * @return 更新的记录数
	 */
	int updateWithoutNull(BsiPhoneProductMapDomain bsiPhoneProductMapDomain);
	
	/**
	 * 更新,要跟新的字段使用字段名，更新条件使用old_字段名，用来适配某些批量更新记录的情况
	 * @param params
	 * @return 更新的记录数
	 */
	int updateDynamic(Map<String,Object> params);
	
	


}