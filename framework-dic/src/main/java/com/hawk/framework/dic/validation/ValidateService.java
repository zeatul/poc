package com.hawk.framework.dic.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.validation.annotation.Constraint;
import com.hawk.framework.dic.validation.validator.ConstraintValidator;
import com.hawk.framework.dic.validation.validator.SingleObjectValidator;
import com.hawk.framework.pub.spring.FrameworkContext;

@Service
public class ValidateService  {

	@Autowired
	private SingleObjectValidator singleObjectValidator;
	

	/**
	 * 校验函数所有参数
	 * @param methodAnnotations 参数注解
	 * @param values 参数值
	 * @throws EmptyParameterRuntimeException 
	 * @throws BeansException 
	 */
	@SuppressWarnings("unchecked")
	public void validMethodParameters (Method method ,Object[] values) {
		if (values == null || values.length == 0)
			return ;
		
		
		Parameter[] parameters = method.getParameters();
		
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
					((ConstraintValidator<Annotation,Object>)(FrameworkContext.getBean(clazz))).valid(annotation, value,null);
					
				}
			}
		}
	}
	
	public void validateObject(Object o){
		singleObjectValidator.valid(null, o, null);
	}

}
