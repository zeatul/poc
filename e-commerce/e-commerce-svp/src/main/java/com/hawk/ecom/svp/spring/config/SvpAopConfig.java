package com.hawk.ecom.svp.spring.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hawk.ecom.svp.service.AOPTestService;

@Configuration
@EnableAspectJAutoProxy
public class SvpAopConfig {
	
	@Bean
	public Validator methodParameterValidator(){
		return new Validator();
	}
	
	@Bean
	public AOPTestService aopTestService(){
		return new AOPTestService();
	}
	
	
	

	@Aspect
	public static class Validator {
		@Pointcut("execution(** com.hawk.ecom.svp.service.*.*(..)) && @annotation(com.hawk.framework.dic.validation.annotation.Valid)")
		public void methodOfValidateParameter() {
		}
		
		@Before("methodOfValidateParameter()")
		public void validate(JoinPoint jp){
			MethodSignature signature = (MethodSignature) jp.getSignature();
			Method method = signature.getMethod();
			Annotation[][] methodAnnotations = method.getParameterAnnotations();
			for (Annotation[] x : methodAnnotations){
				
			}
			
			System.out.println(jp.getSignature().getName());
		}
	}

}
