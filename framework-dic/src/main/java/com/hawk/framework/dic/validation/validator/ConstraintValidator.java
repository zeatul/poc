package com.hawk.framework.dic.validation.validator;

import java.lang.annotation.Annotation;

/**
 * 注解校验接口
 * @author Administrator
 *
 * @param <A>
 * @param <T>
 */
public interface ConstraintValidator <A extends Annotation, T> {
	
	public void valid(A annotation , T value ,String code) ;

}