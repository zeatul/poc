package com.hawk.ecom.svp.persist.mapperex;

import java.util.List;

public interface BsiPhoneModelExMapper {
	
	/**
	 * 列出所有手机品牌
	 * @return 碎屏险支持的手机品牌列表
	 */
	public List<String> queryBrand();

}
