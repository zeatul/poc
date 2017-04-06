package com.hawk.framework.dic.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.utility.tools.StringTools;

public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(NotNull annotation, Object value, String code) throws ValidateException {
		if (value == null) {
			String name = annotation.name();

			if (StringTools.isNullOrEmpty(name)) {
				if (StringTools.isNotNullOrEmpty(annotation.value())) {
					code = annotation.value();
				}
				Word word = dictionaryService.queryWord(code);
				name = word.getName();
			}

			throw new ValidateException("-1", name + "不能为空");
		}

	}

}
