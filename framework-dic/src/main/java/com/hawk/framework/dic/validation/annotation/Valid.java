package com.hawk.framework.dic.validation.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hawk.framework.dic.validation.validator.SingleObjectValidator;

/**
 * Marks a property, method parameter or method return type for validation cascading.
 * <p/>
 * Constraints defined on the object and its properties are be validated when the
 * property, method parameter or method return type is validated.
 * <p/>
 * This behavior is applied recursively.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy={SingleObjectValidator.class})
public @interface Valid {

}
