package com.hawk.framework.dic.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.exception.InvalidWordRuntimeException;
import com.hawk.framework.dic.exception.WordNotFoundRuntimeException;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.utility.tools.BooleanTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class ValidWordValidator implements ConstraintValidator<ValidWord, Object>{
	
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(ValidWord annotation, Object value,String code) throws EmptyParameterRuntimeException {
		

		
		/**
		 * 优先取注解里的code
		 */
		String wordCode = annotation.value();
		
		/**
		 * 注解找不到，才取传入的code
		 */
		if (StringTools.isNullOrEmpty(wordCode)){
			wordCode = code;
		}
		Word word = dictionaryService.queryWord(wordCode);
		
		
		
		if (word == null)
			throw new WordNotFoundRuntimeException(wordCode);
		
		String displayName = annotation.name();
		if (StringTools.isNullOrEmpty(displayName)){
			Synonym synonym = dictionaryService.querySynonym(wordCode);
			if (synonym != null){
				displayName = synonym.getSynonymDisplayName();
			}else{
				displayName = word.getName();
			}
		}
		
		if(value == null){
			if (!annotation.nullable()){
				throw new EmptyParameterRuntimeException(displayName);
			}else{
				return;
			}
		}
		
		if (word.getDataType() == EnumDataType.String){
			checkValue(word,(String)value,displayName);
		}else if (word.getDataType() == EnumDataType.Long){
			
		}else if (word.getDataType() == EnumDataType.Integer){
			
		}else if (word.getDataType() == EnumDataType.Numeric){
			
		}else if (word.getDataType() == EnumDataType.Date){
			
		}
		
	}
	
	private String buildMessage(String displayName ,String patternComment ,String localComment){
		return StringTools.isNotNullOrEmpty(patternComment)?displayName+"必须符合:"+patternComment:displayName +"必须符合:"+localComment;
	}
	
	private void checkValue(Word word , String value ,String displayName){
		Integer maxLength = word.getCharMaxLength();
		Integer minLength = word.getCharMinLength();
		String regex = word.getRegex();
		
		String patternComment = word.getPatternComment();
		
		boolean fixedLength = (maxLength!=null && maxLength.equals(minLength));
		String localComment = "";
		if (fixedLength){
			localComment = "定长"+maxLength;
		}
		
		if (maxLength != null && value.length() > maxLength){
			
			if (!fixedLength){
				localComment = "长度不大于" + maxLength;
			}
			throw new InvalidWordRuntimeException(buildMessage(displayName,patternComment,localComment));
		}
		
		if (minLength!=null && value.length() < minLength){
			if (!fixedLength){
				localComment = "长度不小于" + minLength;
			}
			throw new InvalidWordRuntimeException(buildMessage(displayName,patternComment,localComment));
		}
		
		if (StringTools.isNotNullOrEmpty(regex)){
			if (! value.matches(regex)){
				localComment = "匹配正则表达式"+regex;
				throw new InvalidWordRuntimeException(buildMessage(displayName,patternComment,localComment));
			}
		}
		
		if (BooleanTools.parse(word.getIsEnum())){
			if (word.getEnumValues().get(value) == null){
				StringTools.concatWithSymbol(",", word.getEnumValues().keySet());
				localComment = "值只能是" +"其中之一";
				throw new InvalidWordRuntimeException(buildMessage(displayName,patternComment,localComment));
			}
		}
		
	}

}
