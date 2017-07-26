package com.hawk.ecom.base.persist.mapperex;

import java.util.List;

public interface BsiExMapper {

	/**
	 * 列出所有手机品牌
	 * 
	 * @return 碎屏险支持的手机品牌列表
	 */
	public List<String> queryAllBrand();

}
