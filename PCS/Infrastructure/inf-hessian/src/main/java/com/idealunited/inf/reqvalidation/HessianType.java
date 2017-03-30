/**
 * Project Name:if-hessian
 * File Name : HessianType.java
 * Package Name:com.idealunited.inf.reqvalidation
 * Copyright (c) 2015, www.pay.com All Rights Reserved.
 *
 */
package com.idealunited.inf.reqvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 验证Hessian接口请求中的参数
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {HessianTypeValidator.class}) 
public @interface HessianType {

	String message() default "Message not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	/**
	 * 长度
	 * 
	 * @return
	 */
	int length();
	
	/**
	 * 类型描述
	 * 
	 * @return
	 */
	String type();
	
	/**
	 * 是否强制必填
	 * 
	 * @return
	 */
	boolean mandatory() default false;

}
