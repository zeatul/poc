package com.hawk.framework.dic.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hawk.framework.dic.validation.validator.MinValidator;
import com.hawk.framework.dic.validation.validator.NotEmptyValidator;

@Documented
@Constraint(validatedBy={MinValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Min {

	/**
	 * 数据字典定义的code	
	 * @return
	 */
	String value() default "";
	
	/**
	 * 自定义的字段名
	 * @return
	 */
	String name() default "";
	
	String minValue();
	boolean allowEqaulity() default false; 
}
