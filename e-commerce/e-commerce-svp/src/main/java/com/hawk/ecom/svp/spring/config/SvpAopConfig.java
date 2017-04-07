package com.hawk.ecom.svp.spring.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hawk.ecom.svp.service.AOPTestService;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.service.ParseXmlService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.ValidateService;
import com.hawk.framework.dic.validation.validator.NotEmptyValidator;
import com.hawk.framework.dic.validation.validator.NotNullValidator;
import com.hawk.framework.dic.validation.validator.SingleObjectValidator;


@EnableAspectJAutoProxy
public class SvpAopConfig {
	
	@Bean
	public Validator methodParameterValidator(){
		return new Validator();
	}
	
	@Aspect
	public static class Validator {
		
		@Autowired
		private ValidateService validateService;
		
		@Pointcut("execution(** com.hawk.ecom.svp.service.*.*(..)) && @annotation(com.hawk.framework.dic.validation.annotation.Valid)")
		public void methodOfValidateParameter() {
		}
		
		@Before("methodOfValidateParameter()")
		public void validate(JoinPoint jp) throws ValidateException{
						
			MethodSignature signature = (MethodSignature) jp.getSignature();
			Method method = signature.getMethod();			
			validateService.validMethodParameters(method, jp.getArgs());	
			
			
		}
	}

}
