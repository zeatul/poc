package com.hawk.framework.dic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.utility.tools.CamelNameTools;

/**
 * 加载字典数据 查询字典数据
 * 
 * @author pzhang1
 *
 */
public class DictionaryService {

	private List<String> packageNames;

	private ParseXmlService parseXmlService;

	private Map<String, Word> codeWordMap = new HashMap<String, Word>();

	private Map<String, String> synonymMap = new HashMap<String, String>();

	/**
	 * 加载 指定packageName集合下面的所有字典数据文件
	 * 
	 * @param packageNames
	 * @throws Exception 
	 */
	public DictionaryService(List<String> packageNames, ParseXmlService parseXmlService) throws Exception {
		this.packageNames = packageNames;
		this.parseXmlService = parseXmlService;
		init();
	}

	private void init() throws Exception {
		String[] packageNameArray = packageNames.toArray(new String[] {});
		/*
		 * word
		 */
		List<Word> wordList = parseXmlService.parseWordByPackageName(packageNameArray);
		wordList.forEach(e -> {
			this.codeWordMap.put(CamelNameTools.camelName(e.getCode()), e);
		});

		/**
		 * 
		 */
		Map<String, String> map = parseXmlService.parseWordMapByPackageNames(packageNameArray);
		this.synonymMap.putAll(map);
	}
	
	/**
	 * 查询code对应的word
	 * @param code
	 * @return Word
	 */
	public Word queryWord(String code){
		Word word = codeWordMap.get(code);
		if (word == null){
			word = codeWordMap.get(synonymMap.get(code));
		}
		if (word == null)
			throw new RuntimeException("Couldn't find word which code = "+code);
		return word;
	}
}
