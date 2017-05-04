package com.hawk.framework.dic.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(NotNull annotation, Object value, String code) throws EmptyParameterRuntimeException {
		if (value == null) {
			String name = annotation.name();

			if (StringTools.isNullOrEmpty(name)) {
				
				String wordCode = annotation.value();
				if (StringTools.isNullOrEmpty(wordCode)) {
					wordCode = code;
				}
				Word word = dictionaryService.queryWord(wordCode);
				
				if (word == null) {
					name = wordCode;
				} else {
					name = word.getName();
				}
			}

			throw new EmptyParameterRuntimeException(name);
		}

	}

}
