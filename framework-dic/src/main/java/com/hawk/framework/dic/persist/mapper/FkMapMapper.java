package com.hawk.framework.dic.persist.mapper;
import java.util.List;
import java.util.Map;
import com.hawk.framework.dic.persist.domain.FkMapDomain;
import org.apache.ibatis.annotations.Param;

/**
 * table = t_dic_fk_map
 * desc = 外键字段匹配
 * 
 * @author Gen
 */
public interface FkMapMapper  {

	/**
	 * 根据主键加载记录
	 * @param objectId 主键
	 * @return 查询到的记录
	 */
	FkMapDomain load(@Param("objectId")String objectId );
	
	/**
	 * 动态条件加载记录
	 * @param params 查询条件
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<FkMapDomain> loadDynamic(Map<String,Object> params);
	
	/**
	 * 需要传入分页参数，请使用对应的工具类注入分页参数
	 * @param params
	 * @return 符合条件的记录，如果查询不到记录，返回的是空记录数的List
	 */
	List<FkMapDomain> loadDynamicPaging(Map<String,Object> params);
	
	/**
	 * 计算符合条件的记录数
	 * @param params
	 * @return 符合条件的记录数
	 */
	int count(Map<String,Object> params);
	
	/**
	 * 根据主键查询记录数,用来判断主键对应的记录是否存在
	 * @param objectId 主键
	 * @return 查询到的记录数，0：表示记录不存在，1：表示记录存在
	 */
	int countByPK(@Param("objectId")String objectId );
	
	
	/**
	 * 插入记录
	 * @param FkMapDomain 外键字段匹配
	 * @return 插入的记录数
	 */
	int insert(FkMapDomain fkMapDomain);
	
	/**
	 * 根据主键删除记录
	 * @param objectId 主键
	 * @return 删除的记录数
	 */
	int delete(@Param("objectId")String objectId );
	
	/**
	 * 动态删除记录
	 * @param params 删除条件参数集合
	 * @return 删除的记录数
	 */
	int deleteDynamic(Map<String,Object> params);
	
	/**
	 * 更新,全字段更新,空值被更新成null
	 * @param FkMapDomain 外键字段匹配
	 * @return 更新的记录数
	 */
	int update(FkMapDomain fkMapDomain);
	
	/**
	 * 更新,只更新不为空的值，适合根据主键更新特定字段
	 * @param FkMapDomain 外键字段匹配
	 * @return 更新的记录数
	 */
	int updateWithoutNull(FkMapDomain fkMapDomain);
	
	/**
	 * 更新,要跟新的字段使用字段名，更新条件使用old_字段名，用来适配某些批量更新记录的情况
	 * @param params
	 * @return 更新的记录数
	 */
	int updateDynamic(Map<String,Object> params);
	
	


}