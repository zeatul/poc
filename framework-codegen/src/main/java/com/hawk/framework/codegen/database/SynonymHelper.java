package com.hawk.framework.codegen.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.framework.dic.design.constant.ConstSynonymType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.persist.domain.WordDomain;
import com.hawk.framework.dic.service.SynonymService;
import com.hawk.framework.dic.service.WordService;

public class SynonymHelper {

	private final static Map<String, Synonym> synonymMap = new HashMap<String, Synonym>();
	private final static Map<String, Synonym> savedSynonymMap = new HashMap<String, Synonym>();
	static {
		
		
		
		
		
		
		
	}

	public static void loadFromDatabase(SynonymService synonymService, String synonymType, String systemCode, Integer version) {
		List<Synonym> list = synonymService.loadSynonym(synonymType, systemCode, version);
		list.forEach(synonym -> {
			savedSynonymMap.put(synonym.getSynonymCode(), synonym);
		});
		synonymMap.putAll(savedSynonymMap);
	}

	public static void save(SynonymService synonymService, WordService wordService, String systemCode, Integer version) {
		List<WordDomain> wordDomainList = wordService.loadWordDomain(systemCode, version);
		Map<String, WordDomain> wordDomainMap = new HashMap<String, WordDomain>();
		wordDomainList.forEach(wordDomain -> {
			wordDomainMap.put(wordDomain.getObjectCode(), wordDomain);
		});

		synonymMap.forEach((code, synonym) -> {
			if (!savedSynonymMap.containsKey(code)) {
				
				try {
					synonymService.insertOrUpdate(synonym,wordDomainMap.get(synonym.getOriginCode()).getObjectId(), systemCode, version);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public static Synonym findSynonym(String code) {
		return synonymMap.get(code);
	}

	public static void addWord(String originCode, String synonymCode, String synonymDisplayName) {
		Synonym synonym = new Synonym();
		synonym.setOriginCode(originCode);
		synonym.setSynonymCode(synonymCode);
		synonym.setSynonymType(ConstSynonymType.WORD);
		synonym.setSynonymDisplayName(synonymDisplayName);

		synonymMap.put(synonymCode, synonym);
	}

}
