package com.hawk.framework.dic.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(NotNull annotation, Object value) throws ValidateException {
		if (value == null) {
			String name = annotation.name();

			if (StringTools.isNullOrEmpty(name)) {
				Word word = dictionaryService.queryWord(annotation.value());
				if (word == null){
					name = annotation.value();
				}else{
					name = word.getName();
				}
			}

			throw new ValidateException(-1, name + "不能为空");
		}

	}

}
