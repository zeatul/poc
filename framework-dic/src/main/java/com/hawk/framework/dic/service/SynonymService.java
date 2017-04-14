package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.persist.domain.SynonymDomain;
import com.hawk.framework.dic.persist.mapper.SynonymMapper;
import com.hawk.framework.pub.sql.MybatisParam;

@Service
public class SynonymService {

	private SynonymMapper synonymMapper;

	public List<SynonymDomain> loadSynonymDomain(String synonymType, String systemCode, int version) {
		MybatisParam params = new MybatisParam().put("synonymType", synonymType).put("systemCode", systemCode).put("version", version);
		List<SynonymDomain> synonymDomainList = synonymMapper.loadDynamic(params);
		return synonymDomainList;
	}

	public List<Synonym> loadSynonym(String synonymType, String systemCode, int version) {
		List<SynonymDomain> synonymDomainList = loadSynonymDomain(synonymType,systemCode,version);
		List<Synonym> synonymList = new ArrayList<Synonym>();
		synonymDomainList.forEach(synonymDomain -> {
			Synonym synonym = new Synonym();
			synonym.setOriginCode(synonymDomain.getOriginObjectCode());
			synonym.setSynonymCode(synonymDomain.getSynonymObjectCode());
			synonym.setSynonymType(synonymDomain.getSynonymType());
			synonymList.add(synonym);
		});
		return synonymList;
	}
}
