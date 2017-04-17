package com.hawk.ecom.svp.persist.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import org.apache.ibatis.annotations.Param;

/**
 * table = t_svp_mobile_data_order_detail
 * desc = 联通流量订单明细
 * 
 * @author Gen
 */
public interface MobileDataOrderDetailMapper  {

	
	/**
	 * 动态条件加载记录
	 * @param params 查询条件
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<MobileDataOrderDetailDomain> loadDynamic(Map<String,Object> params);
	
	/**
	 * 需要传入分页参数，请使用对应的工具类注入分页参数
	 * @param params
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<MobileDataOrderDetailDomain> loadDynamicPaging(Map<String,Object> params);
	
	/**
	 * 计算符合条件的记录数
	 * @param params
	 * @return 符合条件的记录数
	 */
	int count(Map<String,Object> params);
	
	
	
	/**
	 * 插入记录
	 * @param MobileDataOrderDetailDomain 联通流量订单明细
	 * @return 插入的记录数
	 */
	int insert(MobileDataOrderDetailDomain mobileDataOrderDetailDomain);
	
	
	/**
	 * 动态删除记录
	 * @param params 删除条件参数集合
	 * @return 删除的记录数
	 */
	int deleteDynamic(Map<String,Object> params);
	
	/**
	 * 更新,全字段更新,空值被更新成null
	 * @param MobileDataOrderDetailDomain 联通流量订单明细
	 * @return 更新的记录数
	 */
	int update(MobileDataOrderDetailDomain mobileDataOrderDetailDomain);
	
	/**
	 * 更新,只更新不为空的值，适合根据主键更新特定字段
	 * @param MobileDataOrderDetailDomain 联通流量订单明细
	 * @return 更新的记录数
	 */
	int updateWithoutNull(MobileDataOrderDetailDomain mobileDataOrderDetailDomain);
	
	/**
	 * 更新,要跟新的字段使用字段名，更新条件使用old_字段名，用来适配某些批量更新记录的情况
	 * @param params
	 * @return 更新的记录数
	 */
	int updateDynamic(Map<String,Object> params);
	
	


}