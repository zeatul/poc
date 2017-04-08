package com.hawk.framework.pub.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FrameworkContext implements ApplicationContextAware{

	private static ApplicationContext applicationContext ;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		FrameworkContext.applicationContext = applicationContext;
	}
	
	public static Object getBean(String id){
		return applicationContext.getBean(id);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}

}
