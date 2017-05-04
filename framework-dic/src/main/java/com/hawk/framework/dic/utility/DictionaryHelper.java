package com.hawk.framework.dic.utility;

import java.util.Date;
import java.util.UUID;

import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.persist.domain.SynonymDomain;
import com.hawk.framework.dic.persist.domain.WordDomain;
import com.hawk.framework.dic.persist.domainex.SynonymExDomain;

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
		
		word.setPatternComment(wordDomain.getObjectPatternComment());
		word.setPattern(wordDomain.getObjectPattern());
		word.setExtraValidation(wordDomain.getObjectExtraValidation());
		
		word.setEnumValues(wordDomain.getEnumValues());
		
		
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
		
		wordDomain.setEnumValues(word.getEnumValuesAsString());
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
		
		wordDomain.setObjectPattern(word.getPattern());
		wordDomain.setObjectPatternComment(word.getPatternComment());
		wordDomain.setObjectExtraValidation(word.getExtraValidation());
		
		wordDomain.setCreateDate(new Date());
		wordDomain.setUpdateDate(wordDomain.getCreateDate());
		return wordDomain;
	}

	public static Synonym convert(SynonymExDomain SynonymExDomain){
		Synonym synonym = new Synonym();
		synonym.setOriginCode(SynonymExDomain.getOriginObjectCode());
		synonym.setSynonymCode(SynonymExDomain.getSynonymObjectCode());
		synonym.setSynonymType(SynonymExDomain.getSynonymType());
		return synonym;
	}
	
	public static SynonymDomain convert(Synonym synonym ,String originObjectId,String systemCode ,int version){
		SynonymDomain synonymDomain = new SynonymDomain();
		synonymDomain.setObjectId(UUID.randomUUID().toString());
		synonymDomain.setOriginObjectId(originObjectId);
		synonymDomain.setSynonymDisplayName(synonym.getSynonymDisplayName());
		synonymDomain.setSynonymObjectCode(synonym.getSynonymCode());
		synonymDomain.setSynonymType(synonym.getSynonymType());		
		synonymDomain.setCreateDate(new Date());
		synonymDomain.setUpdateDate(synonymDomain.getCreateDate());
		
		return synonymDomain;
	}
}
