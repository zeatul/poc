package com.hawk.ecom.user.service;

import org.springframework.stereotype.Service;

import com.hawk.ecom.user.exception.UserNotLoginRuntimeException;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.validator.ConstraintValidator;

@Service
public class NotLoginValidator implements ConstraintValidator<NotNull, Object> {

	

	@Override
	public void valid(NotNull annotation, Object value, String code) throws EmptyParameterRuntimeException {
		if (value == null) {
			throw new UserNotLoginRuntimeException();
		}

	}

}
