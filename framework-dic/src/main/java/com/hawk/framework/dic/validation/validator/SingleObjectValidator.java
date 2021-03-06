package com.hawk.framework.dic.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.validation.annotation.Constraint;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.spring.FrameworkContext;

@Service
public class SingleObjectValidator implements ConstraintValidator<Valid, Object> {
	
	@SuppressWarnings("unchecked")
	@Override
	public void valid(Valid annotation, Object value,String code) throws EmptyParameterRuntimeException {
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
					((ConstraintValidator<Annotation,Object>)(FrameworkContext.getBean(clazz))).valid(fieldAnnotation, o,field.getName());
					
				}
			}
		}
		
	}

	

}
