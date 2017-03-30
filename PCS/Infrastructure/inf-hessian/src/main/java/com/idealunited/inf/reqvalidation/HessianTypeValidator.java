/**
 * Project Name:if-hessian
 * File Name : HessianTypeValidator.java
 * Package Name:com.idealunited.inf.reqvalidation
 * Copyright (c) 2015, www.pay.com All Rights Reserved.
 *
 */
package com.idealunited.inf.reqvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public class HessianTypeValidator implements
		ConstraintValidator<HessianType, Object> {

	private static final TypeMatcher a = new _a();
	private static final TypeMatcher n = new _n();
	private static final TypeMatcher s = new _s();
	private static final TypeMatcher an = new _an();
	private static final TypeMatcher ans = new _ans();
	private static final TypeMatcher as = new _as();

	private String type;

	private TypeMatcher matcher;

	private int length;

	private boolean mandatory;

	@Override
	public void initialize(HessianType constraintAnnotation) {
		this.type = constraintAnnotation.type();
		this.length = constraintAnnotation.length();
		this.mandatory = constraintAnnotation.mandatory();
		this.matcher = createMatcher(type);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		String errorMessage = "[" + value + "]:";

		if (mandatory) {
			if (StringUtils.isBlank(String.valueOf(value))) {
				errorMessage += "could not be null";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(errorMessage)
						.addConstraintViolation();
				return false;
			}
		} else {
			if (value == null) {
				return true;
			}
		}

		if (length > 0) {
			if (String.valueOf(value).length() > length) {
				errorMessage += "length should less equal than " + length;
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(errorMessage)
						.addConstraintViolation();
				return false;
			}
		}

		if (!matcher.match(String.valueOf(value))) {
			errorMessage += "not constraint with type " + type;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(errorMessage)
					.addConstraintViolation();
			return false;
		}

		return true;
	}

	private TypeMatcher createMatcher(String type) {

		if (StringUtils.equals("a", type)) {
			return a;
		} else if (StringUtils.equals("n", type)) {
			return n;
		} else if (StringUtils.equals("s", type)) {
			return s;
		} else if (StringUtils.equals("an", type)) {
			return an;
		} else if (StringUtils.equals("as", type)) {
			return as;
		} else if (StringUtils.equals("ans", type)) {
			return ans;
		} else {
			return new _date(type);
		}

	}

}