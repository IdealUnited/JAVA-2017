package com.idealunited.util;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * JSR 303验证类。
 * @author Kingo
 *
 */
public class ValidatorUtil {

	private static 	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	private static Validator validator = vf.getValidator();
	
	/**
	 * 使用默认的验证器来验证给定的Pojo对象
	 * @param <T> 需要验证的类型
	 * @param t 需要验证的对象
	 * @return 验证的结果集
	 */
	public static  <T>  Set<ConstraintViolation<T>> validator(T t){
		return validator.validate(t);
	}

}
