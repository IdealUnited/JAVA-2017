package com.idealunited.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectUtils {

	private static final Log logger = LogFactory.getLog(ReflectUtils.class);

	public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname) || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();

		try {
			Field field = clazz.getDeclaredField(fname);
			if (!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
			}
			field.set(target, fvalue);
		} catch (Exception fe) {
			if (logger.isDebugEnabled()) {
				logger.debug(fe);
			}
			try {
				Method method = clazz.getDeclaredMethod("set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1), ftype);
				if (!Modifier.isPublic(method.getModifiers())) {
					method.setAccessible(true);
				}
				method.invoke(target, fvalue);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e);
				}
			}

		}

	}
}
