package com.hawk.framework.dic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.hawk.framework.dic.design.constant.ConstSynonymType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.utility.tools.CamelNameTools;

/**
 * 加载字典数据 查询字典数据
 * 
 * @author pzhang1
 *
 */
public class DictionaryService {

	private String systemCode;

	private int version;

	@Autowired
	private WordService wordService;

	@Autowired
	private SynonymService synonymService;

	private Map<String, Word> codeWordMap = new HashMap<String, Word>();

	private Map<String, Synonym> synonymMap = new HashMap<String, Synonym>();

	/**
	 * 加载 指定packageName集合下面的所有字典数据文件
	 * 
	 * @param packageNames
	 * @throws Exception
	 */
	public DictionaryService(String systemCode, int version) throws Exception {
		this.systemCode = systemCode;
		this.version = version;
	}

	@PostConstruct
	private void init() throws Exception {
		/**
		 * synonym
		 */
		List<Synonym> synonymList = synonymService.loadSynonym(ConstSynonymType.WORD, systemCode, version);
		synonymList.forEach(synonym -> {
			synonymMap.put(synonym.getSynonymCode(), synonym);
		});

		/*
		 * word
		 */
		List<Word> wordList = wordService.loadWord(systemCode, version);

		wordList.forEach(e -> {
			this.codeWordMap.put(CamelNameTools.camelName(e.getCode()), e);
			this.codeWordMap.put(e.getCode(), e);
		});

	}

	/**
	 * 查询code对应的word
	 * 
	 * @param code
	 * @return Word
	 */
	public Word queryWord(String code) {
		Word word = codeWordMap.get(code);
		if (word == null) {
			Synonym synonym = synonymMap.get(code);
			if (synonym != null) {
				String originCode = synonym.getOriginCode();
				if (originCode != null) {
					word = codeWordMap.get(originCode);
				}
			}
		}
		return word;
	}
	
	public Synonym querySynonym(String code){
		Synonym synonym = synonymMap.get(code);
		return synonym;
	}
}
