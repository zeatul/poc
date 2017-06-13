package com.hawk.ecom.muser.service;

import org.springframework.stereotype.Service;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.ecom.muser.exception.MallUserNotLoginRuntimeException;
import com.hawk.framework.dic.validation.validator.ConstraintValidator;

@Service
public class MallNotLoginValidator implements ConstraintValidator<MallNotLogin, Object> {

	

	@Override
	public void valid(MallNotLogin annotation, Object value, String code)  {
		if (value == null) {
			throw new MallUserNotLoginRuntimeException();
		}

	}

}
