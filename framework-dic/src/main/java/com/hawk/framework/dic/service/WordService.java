package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.persist.domain.WordDomain;
import com.hawk.framework.dic.persist.mapper.WordMapper;
import com.hawk.framework.dic.utility.DictionaryHelper;
import com.hawk.framework.pub.sql.MybatisParam;

@Service
public class WordService {

	@Autowired
	private WordMapper wordMapper;
	
	
	public List<WordDomain> loadWordDomain(String systemCode,int version){
		MybatisParam params = new MybatisParam().put("systemCode", systemCode).put("version", version);
		return wordMapper.loadDynamic(params);
	}
	
	public List<Word> loadWord(String systemCode,int version){
		List<WordDomain> wordDomainList = loadWordDomain(systemCode,version);
		List<Word> wordList = new ArrayList<Word>();
		wordDomainList.forEach(wordDomain->{
			Word word = DictionaryHelper.convert(wordDomain);
			wordList.add(word);
		});
		return wordList;
	}
	
	public void insertOrUpdateWord(Word word ,String systemCode,int version){
		WordDomain wordDomain = DictionaryHelper.convert(word, systemCode, version);
		wordMapper.insert(wordDomain);
	}
}
