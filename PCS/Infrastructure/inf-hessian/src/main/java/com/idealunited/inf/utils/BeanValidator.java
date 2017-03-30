/**
 * 
 */
package com.idealunited.inf.utils;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.inf.excepiton.HessianInvokeException;

/**
 * @author new
 * 
 */
public class BeanValidator {

	public static <T> void validate(T bean) throws HessianInvokeException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(bean);

		StringBuffer sbf = new StringBuffer();

		for (Iterator<ConstraintViolation<T>> iterator = constraintViolations
				.iterator(); iterator.hasNext();) {
			ConstraintViolation<T> constraintViolation = iterator.next();
			sbf.append(constraintViolation.getPropertyPath());
			sbf.append(":");
			sbf.append(constraintViolation.getMessage());
			sbf.append(";");
		}
		if (sbf.length() > 0) {
			throw new HessianInvokeException(
					ResponseCodeEnum.INVALID_PARAM.getCode(),
					sbf.toString());
		}

	}

}
