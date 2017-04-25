package com.hawk.framework.dic.utility;

import java.util.Date;

import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.persist.domain.SynonymDomain;
import com.hawk.framework.dic.persist.domain.WordDomain;

public class DictionaryHelper {
	
	public static Word convert(WordDomain wordDomain){
		Word word = new Word();
		word.setCharMaxLength(wordDomain.getCharMaxLength());
		word.setCharMinLength(wordDomain.getCharMinLength());
		word.setCode(wordDomain.getObjectCode());
		word.setComment(wordDomain.getObjectComment());
		word.setDataType(EnumDataType.parse(wordDomain.getDataType()));
		word.setDatetimePrecision(wordDomain.getDatetimePrecision());
		word.setDisplayName(wordDomain.getObjectDisplayName());
		word.setEnumKey(wordDomain.getEnumKey());
		word.setEnumValue(wordDomain.getEnumValue());
		word.setId(wordDomain.getObjectId());
		word.setIsEnum(wordDomain.getIsEnum());
		word.setIsOnlyAscii(wordDomain.getIsOnlyAscii());
		word.setMaxValue(wordDomain.getMaxValue());
		word.setMinValue(wordDomain.getMinValue());
		word.setName(wordDomain.getObjectName());
		word.setNumericPrecision(wordDomain.getNumericPrecision());
		word.setNumericScale(wordDomain.getNumericScale());
		word.setRegex(wordDomain.getRegex());
		word.setUseType(wordDomain.getUseType());
		return word;
	}
	
	public static WordDomain convert(Word word,String systemCode ,int version){
		WordDomain wordDomain = new WordDomain();
		wordDomain.setCharMaxLength(word.getCharMaxLength());
		wordDomain.setCharMinLength(word.getCharMinLength());
		wordDomain.setObjectCode(word.getCode());
		wordDomain.setObjectComment(word.getComment());
		wordDomain.setDataType(word.getDataType().getValue());
		wordDomain.setDatetimePrecision(word.getDatetimePrecision());
		wordDomain.setObjectDisplayName(word.getDisplayName());
		wordDomain.setEnumKey(word.getEnumKey());
		wordDomain.setEnumValue(word.getEnumValue());
		wordDomain.setObjectId(word.getId());
		wordDomain.setIsEnum(word.getIsEnum());
		wordDomain.setIsOnlyAscii(word.getIsOnlyAscii());
		wordDomain.setMaxValue(word.getMaxValue());
		wordDomain.setMinValue(word.getMinValue());
		wordDomain.setObjectName(word.getName());
		wordDomain.setNumericPrecision(word.getNumericPrecision());
		wordDomain.setNumericScale(word.getNumericScale());
		wordDomain.setRegex(word.getRegex());
		wordDomain.setUseType(word.getUseType());
		wordDomain.setSystemCode(systemCode);
		wordDomain.setVersion(version);
		
		wordDomain.setCreateDate(new Date());
		wordDomain.setUpdateDate(wordDomain.getCreateDate());
		return wordDomain;
	}

	public static Synonym convert(SynonymDomain SynonymDomain){
		Synonym synonym = new Synonym();
		synonym.setOriginCode(SynonymDomain.getOriginObjectCode());
		synonym.setSynonymCode(SynonymDomain.getSynonymObjectCode());
		synonym.setSynonymType(SynonymDomain.getSynonymType());
		return synonym;
	}
}
