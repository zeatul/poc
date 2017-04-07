package com.hawk.framework.dic.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.Constraint;
import com.hawk.framework.dic.validation.annotation.Valid;

public class SingleObjectValidator implements ConstraintValidator<Valid, Object> ,ApplicationContextAware{
	
	@SuppressWarnings("unchecked")
	@Override
	public void valid(Valid annotation, Object value) throws ValidateException {
		/**
		 * 复杂对象，code 没有意义
		 */
		
		if (value == null)
			return ;
		
		Field[] fields = value.getClass().getDeclaredFields();
		
		for (Field field : fields){
			for (Annotation fieldAnnotation : field.getAnnotations()){
				Constraint c = fieldAnnotation.annotationType().getDeclaredAnnotation(Constraint.class);
				if ( c==null)
					continue;
				
				Object o = null;
				try {
					field.setAccessible(true);
					o = field.get(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}

				for (int j =0; j<c.validatedBy().length; j++){
					Class<?> clazz  = c.validatedBy()[j] ;
					((ConstraintValidator<Annotation,Object>)(applicationContext.getBean(clazz))).valid(fieldAnnotation, o);
					
				}
			}
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	private ApplicationContext applicationContext;

}
