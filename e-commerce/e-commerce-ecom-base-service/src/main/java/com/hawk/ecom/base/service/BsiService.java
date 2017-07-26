package com.hawk.ecom.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.base.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.base.persist.mapper.BsiPhoneModelMapper;
import com.hawk.ecom.base.persist.mapperex.BsiExMapper;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class BsiService {

	@Autowired
	private BsiExMapper bsiExMapper;

	@Autowired
	private BsiPhoneModelMapper bsiPhoneModelMapper;;

	/**
	 * 列出所有手机品牌
	 * 
	 * @return
	 */
	public List<String> queryAllPhoneBrand() {
		return bsiExMapper.queryAllBrand();
	}

	/**
	 * 列出指定手机品牌对应的型号
	 * 
	 * @param brand
	 *            手机品牌
	 * @return 手机型号
	 */
	@Valid
	public List<BsiPhoneModelDomain> queryPhoneModel(@NotEmpty("bsi_phone_brand") String brand) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bsiPhoneBrand", brand);
		return bsiPhoneModelMapper.loadDynamic(params);
	}

	

}
