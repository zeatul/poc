package com.hawk.framework.dic.validation.validator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object>{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	public void valid(NotEmpty annotation, Object value) throws ValidateException {
		boolean isValid = isValid(annotation,value);
		if (!isValid){
			
			String name = annotation.name(); //开发指定名称不需要排序
			
			if (StringTools.isNullOrEmpty(name)){
				
				Word word = dictionaryService.queryWord(annotation.value());
				if (word == null){
					name = annotation.value();
				}else{
					name = word.getName();
				}
			}
			
			
			
			
			throw new ValidateException(-1,name +"不能为空");
		}
	}

	private boolean isValid(NotEmpty annotation, Object value ) throws ValidateException {
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
		
		throw new ValidateException(-1,"unsupport object type for empty checking");
	}

	

}

