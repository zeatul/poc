package com.hawk.ecom.svp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.persist.mapper.BsiPhoneModelMapper;
import com.hawk.ecom.svp.persist.mapperex.BsiPhoneModelExMapper;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

@Service
public class BsiPhoneModelService {
	
	@Autowired
	private BsiPhoneModelExMapper bsiPhoneModelExMapper;
	
	@Autowired
	private BsiPhoneModelMapper bsiPhoneModelMapper;
	
	
	/**
	 * 列出所有品牌
	 * @return
	 */
	public List<String> queryPhoneBrand(){
		return bsiPhoneModelExMapper.queryBrand();
	}
	
	/**
	 * 列出指定手机品牌对应的型号
	 * @param brand 手机品牌
	 * @return 手机型号
	 */
	public List<BsiPhoneModelDomain> queryPhoneModel(@NotEmpty("brand") String brand){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bsiPhoneBrand", brand);
		return bsiPhoneModelMapper.loadDynamic(params);
	}
	
	/**
	 * 查询指定品牌和型号的手机信息
	 * @param brand
	 * @param model
	 * @return
	 */
	public int queryPhoneModelId(@NotEmpty("brand") String brand,@NotEmpty("model") String model){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bsiPhoneBrand", brand);
		params.put("bsiPhoneModel", model);
		List<BsiPhoneModelDomain>  list = bsiPhoneModelMapper.loadDynamic(params);
		if (list.size() == 0)
			throw new RuntimeException("Couldn't find the phone model");
		
		return list.get(0).getBsiPhoneModelId();
	}

}
