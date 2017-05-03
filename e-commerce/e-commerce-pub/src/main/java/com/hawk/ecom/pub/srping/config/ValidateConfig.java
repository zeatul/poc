package com.hawk.ecom.pub.srping.config;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.ValidateService;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = { ValidateService.class })
public class ValidateConfig {
	
	
	@Bean	
	public DictionaryService dictionaryService() throws Exception{
		String systemCode = "ecom";
		int version = 1;
		DictionaryService dictionaryService = new DictionaryService(systemCode,version);
		return dictionaryService;
	}
	
	
	
	
	
	
	
	@Bean
	public Validator methodParameterValidator(){
		return new Validator();
	}
	
	@Aspect
	public static class Validator {
		
		@Autowired
		private ValidateService validateService;
		
		@Pointcut("execution(** com.hawk.ecom.*.service.*.*(..)) && @annotation(com.hawk.framework.dic.validation.annotation.Valid)")
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
