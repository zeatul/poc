package com.hawk.framework.dic.validation.validator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.Min;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MinValidator implements ConstraintValidator<Min, Object>{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	public void valid(Min annotation, Object value,String code) throws EmptyParameterRuntimeException {
		boolean isValid = isValid(annotation,value);
		if (!isValid){
			
			String name = annotation.name(); //开发指定名称不需要排序
			
			if (StringTools.isNullOrEmpty(name)){
				
				String wordCode = annotation.value();
				
				if (StringTools.isNullOrEmpty(wordCode)){
					wordCode = code;
				}
				Word word = dictionaryService.queryWord(wordCode);
				
				if (word == null){
					name = wordCode ;
				}else{
					name = word.getName();
				}
			}
			
			
			throw new EmptyParameterRuntimeException(name);
		}
	}

	private boolean isValid(Min annotation, Object value ) throws EmptyParameterRuntimeException {
		if (value == null)
			return true;
		
		
		
		if (value instanceof Integer){			
			Integer v = (Integer)value;
			Integer minValue = Integer.parseInt(annotation.minValue());
			if (v > minValue){
				return true;
			}
			if (v == minValue && annotation.allowEqaulity()){
				return true;
			}
			return false;
		}
		
		
		
		throw new RuntimeException("unsupport object type for min value checking");
	}

	

}

