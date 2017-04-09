package com.hawk.ecom.svp.spring.config;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.ValidateService;


@EnableAspectJAutoProxy
public class SvpAopConfig {
	
	

}
