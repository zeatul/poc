package com.hawk.framework.dic.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.Constraint;
import com.hawk.framework.dic.validation.annotation.ValidCollection;
import com.hawk.framework.pub.exception.BasicRuntimeException;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

@Service
/**
 * 校验集合类参数 ,Collection 类 和  数组
 * @author Administrator
 *
 */
public class MulitiObjectValidator implements ConstraintValidator<ValidCollection, Object> {

	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	public void valid(ValidCollection annotation, Object value, String code) {

		if (value == null) {
			return;
		}

		if (value instanceof Collection) {
			Collection<?> c = (Collection<?>) value;
			int i = 0;
			for (Object o : c){
				validObject(o, i,annotation,code);
				i++;
			}
			
			return;
		}

		if (value.getClass().isArray()) {
			Object[] o = (Object[]) value;
			for (int i = 0; i < o.length; i++) {
				validObject(o, i,annotation,code);
			}
			return;
		}

		throw new RuntimeException("unsupport object type for Muliti Object checking");

	}

	@SuppressWarnings("unchecked")
	private void validObject(Object value, int i,ValidCollection annotation, String code) {
		Field[] fields = value.getClass().getDeclaredFields();

		for (Field field : fields) {
			for (Annotation fieldAnnotation : field.getAnnotations()) {
				Constraint c = fieldAnnotation.annotationType().getDeclaredAnnotation(Constraint.class);
				if (c == null)
					continue;

				Object o = null;
				try {
					field.setAccessible(true);
					o = field.get(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}

				for (int j = 0; j < c.validatedBy().length; j++) {
					Class<?> clazz = c.validatedBy()[j];
					try{
						((ConstraintValidator<Annotation, Object>) (FrameworkContext.getBean(clazz))).valid(fieldAnnotation, o, field.getName());

					}catch(BasicRuntimeException ex){
						String name = annotation.name();

						if (StringTools.isNullOrEmpty(name)) {
							
							String wordCode = annotation.value();
							if (StringTools.isNullOrEmpty(wordCode)) {
								wordCode = code;
							}
							Word word = dictionaryService.queryWord(wordCode);
							
							if (word == null) {
								name = wordCode;
							} else {
								name = word.getName();
							}
						}
						throw new BasicRuntimeException(ex.getCode(),ex.getMessage());
					}
				}
			}
		}
	}

}
