package com.hawk.framework.dic.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.Word;

@Service
public class WordValidator implements ConstraintValidator<Word, Object>{
	
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(Word annotation, Object value) throws ValidateException {
		// TODO Auto-generated method stub
		
	}

}
