package com.hawk.framework.dic.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hawk.framework.dic.validation.validator.ConstraintValidator;

/**
 * 标准注解为验证用注解
 * @author pzhang1
 *
 */
@Documented
@Target({ ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface Constraint {
	Class<? extends ConstraintValidator<? extends Annotation, ?>>[] validatedBy();
}
