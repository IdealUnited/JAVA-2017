package com.idealunited.common;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class HttpRequestUtils {

	/**
	 * 将request中请求参数转换成对象
	 * 
	 * @param request
	 * @param targetClass
	 * @return
	 */
	public static <T> T convert(Class<T> targetClass, HttpServletRequest request) {

		Map<String, String> paraMap = new HashMap(request.getParameterMap());

		try {
			T targetObject = targetClass.newInstance();
			BeanWrapper bean = new BeanWrapperImpl(targetObject);

			PropertyDescriptor[] propertyDescriptors = bean
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String propertyName = property.getDisplayName();
				Object value = paraMap.get(propertyName);
				if (value instanceof String[]) {
					String[] tem = (String[]) value;
					value = tem[0];
				}
				if (!"class".equals(propertyName)) {
					bean.setPropertyValue(propertyName, value);
				}

			}
			return targetObject;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Map convert(Map paraMap) {
		Map resultMap = new HashMap();

		for (Object key : paraMap.keySet()) {
			Object value = paraMap.get(key);
			if (value instanceof String) {
				resultMap.put(key, value);
			} else if (value instanceof String[]) {
				String[] tem = (String[]) value;
				if (null != value && tem.length > 0) {
					resultMap.put(key, tem[0]);
				}
			}
		}
		return resultMap;
	}
}
