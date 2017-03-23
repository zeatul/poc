package com.hawk.ecom.svp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.mapperex.BsiPhoneModelExMapper;

@Service
public class BsiService {
	
	@Autowired
	private BsiPhoneModelExMapper bsiPhoneModelExMapper;

	/**
	 * 列出所有品牌
	 * @return
	 */
	public List<String> queryBrand(){
		return bsiPhoneModelExMapper.queryBrand();
	}
}
