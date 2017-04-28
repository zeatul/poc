package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.persist.domain.SynonymDomain;
import com.hawk.framework.dic.persist.domainex.SynonymExDomain;
import com.hawk.framework.dic.persist.mapper.SynonymMapper;
import com.hawk.framework.dic.persist.mapperex.SynonymExMapper;
import com.hawk.framework.dic.utility.DictionaryHelper;
import com.hawk.framework.pub.sql.MybatisParam;

@Service
public class SynonymService {

	@Autowired
	private SynonymMapper synonymMapper;
	
	@Autowired
	private SynonymExMapper synonymExMapper;

	public List<SynonymDomain> loadSynonymDomain(String synonymType, String systemCode, int version) {
		MybatisParam params = new MybatisParam().put("synonymType", synonymType).put("systemCode", systemCode).put("version", version);
		List<SynonymDomain> synonymDomainList = synonymMapper.loadDynamic(params);
		return synonymDomainList;
	}

	public List<Synonym> loadSynonym(String synonymType, String systemCode, int version) {
		List<SynonymExDomain> synonymExDomainList = synonymExMapper.querySynonymEx(synonymType,systemCode,version);
		List<Synonym> synonymList = new ArrayList<Synonym>();
		synonymExDomainList.forEach(synonymDomain -> {			
			synonymList.add(DictionaryHelper.convert(synonymDomain));
		});
		return synonymList;
	}
	
	public void insertOrUpdate(Synonym synonym,String originObjectId , String systemCode, int version){
		SynonymDomain synonymDomain = DictionaryHelper.convert(synonym,originObjectId,systemCode,version);
		synonymMapper.insert(synonymDomain);
	}
}
