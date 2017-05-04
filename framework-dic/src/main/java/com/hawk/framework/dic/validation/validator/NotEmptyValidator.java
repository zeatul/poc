package com.hawk.framework.dic.validation.validator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object>{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	public void valid(NotEmpty annotation, Object value,String code) throws EmptyParameterRuntimeException {
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

	private boolean isValid(NotEmpty annotation, Object value ) throws EmptyParameterRuntimeException {
		if (value == null)
			return false;
		
		
		
		if (value instanceof String){			String str = (String)value;
			return StringTools.isNotNullOrEmpty(str);
		}
		
		if (value instanceof Collection){
			Collection<?> c = (Collection<?>)value;
			if (c.size() == 0)
				return false;
			else
				return true;
		}
		
		if (value.getClass().isArray()){
			Object[] o = (Object[])value;
			if (o.length == 0)
				return false;
			else
				return true;
		}
		
		throw new RuntimeException("unsupport object type for empty checking");
	}

	

}

