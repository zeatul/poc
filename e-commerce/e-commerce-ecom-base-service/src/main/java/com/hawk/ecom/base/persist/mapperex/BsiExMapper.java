package com.hawk.ecom.base.persist.mapperex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.base.persist.domainex.BsiProductExDomain;

public interface BsiExMapper {

	/**
	 * 列出所有手机品牌
	 * 
	 * @return 碎屏险支持的手机品牌列表
	 */
	public List<String> queryAllBrand();
	
	/**
	 * 查询指定手机型号，对应的产品
	 * @param bsiPhoneModelId
	 * @return
	 */
	List<BsiProductExDomain> queryProductByPhoneModelId(@Param("bsiPhoneModelId") Integer bsiPhoneModelId);

	
}
