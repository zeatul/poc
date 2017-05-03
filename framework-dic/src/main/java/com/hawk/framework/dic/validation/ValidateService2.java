//package com.hawk.framework.dic.validation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.hawk.framework.dic.design.data.EnumDataType;
//import com.hawk.framework.dic.design.data.Word;
//import com.hawk.framework.dic.service.DictionaryService;
//import com.hawk.framework.utility.tools.StringTools;
//
//public class ValidateService2 {
//	
//	@Autowired
//	private DictionaryService dictionaryService;
//	
//	public Object validate(String code, Object value  ){
//		Word word = dictionaryService.queryWord(code);
//		
//		if (word.getDataType() == EnumDataType.String){
//			return validateString(word,(String)value);
//		}else if (word.getDataType() == EnumDataType.Integer){
//			return validateInteger(word,(Integer)value);
//		}else if (word.getDataType() == EnumDataType.Long){		
//			return validateLong(word,(Long)value);
//		}else{		
//			throw new RuntimeException("Unsupported data type");
//		}
//	}
//	
//	private Integer validateInteger(Word word ,Integer value){
//		String strMaxValue = word.getMaxValue();
//		String strMinValue = word.getMinValue();
//		if (StringTools.isNotNullOrEmpty(strMaxValue)){
//			if (value >  Integer.parseInt(strMaxValue))
//				throw new RuntimeException(" greater than maxvalue " + strMaxValue);
//		}
//		if (StringTools.isNotNullOrEmpty(strMinValue)){
//			if (value < Integer.parseInt(strMinValue))
//				throw new RuntimeException(" less than  minvalue " + strMinValue);
//		}
//		
//		
//		return value;
//		
//	}
//	
//	private Long validateLong(Word word ,Long value){
//		String strMaxValue = word.getMaxValue();
//		String strMinValue = word.getMinValue();
//		if (StringTools.isNotNullOrEmpty(strMaxValue)){
//			if (value >  Long.parseLong(strMaxValue))
//				throw new RuntimeException(" greater than maxvalue " + strMaxValue);
//		}
//		if (StringTools.isNotNullOrEmpty(strMinValue)){
//			if (value < Long.parseLong(strMinValue))
//				throw new RuntimeException(" less than  minvalue " + strMinValue);
//		}
//		
//		
//		return value;
//		
//	}
//	
//	private String validateString(Word word, String value){
//		value = value.trim();
//		int maxLength = word.getCharMaxLength();
//		int minLength = word.getCharMinLength();
//		if (value.length() > maxLength)
//			throw new RuntimeException();
//		if (value.length() < minLength)
//			throw new RuntimeException();
//		
//		return value;
//	}
//	
//	
//
//}
