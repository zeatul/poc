package com.hawk.framework.dic.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hawk.framework.dic.validation.annotation.Constraint;
import com.hawk.framework.dic.validation.validator.ConstraintValidator;

public class ValidateService implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	/**
	 * 校验函数所有参数
	 * @param methodAnnotations 参数注解
	 * @param values 参数值
	 * @throws ValidateException 
	 * @throws BeansException 
	 */
	@SuppressWarnings("unchecked")
	public void validMethodParameters (Method method ,Object[] values) throws ValidateException{
		if (values == null || values.length == 0)
			return ;
		
		
		Parameter[] parameters = method.getParameters();R
		
		for(int i=0 ; i< values.length; i++){
			Object value = values[i];
			Parameter parameter = parameters[i];
		
			Annotation[] annotations = parameter.getAnnotations();
			
			for (Annotation annotation : annotations){
				Constraint c = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
				if ( c==null)
					continue;

				for (int j =0; j<c.validatedBy().length; j++){
					Class<?> clazz  = c.validatedBy()[j] ;
					((ConstraintValidator<Annotation,Object>)(applicationContext.getBean(clazz))).valid(annotation, value);
					
				}
			}
		}
	}

}
