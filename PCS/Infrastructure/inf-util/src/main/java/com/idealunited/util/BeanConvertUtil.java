package com.idealunited.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Bean属性转换工具类.
 * 
 */
public class BeanConvertUtil {

	private static final Log logger = LogFactory.getLog(BeanConvertUtil.class);

	/**
	 * 把Bean数据转换为另外一个类的对象中.
	 * 
	 * @param targetClass
	 *            目标对象的类,必须有public 的 无参数的 construct method.
	 * @param sourceObject
	 *            数据源
	 * @return 数据目标
	 */
	public static <T> T convert(Class<T> targetClass, Object sourceObject) {
		if (sourceObject == null) {
			return null;
		}
		try {
			T targetObject = targetClass.newInstance();
			BeanUtils.copyProperties(sourceObject, targetObject);

			return targetObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把Bean数据转换为另外一个类的对象集合中.
	 * 
	 * @param targetClass
	 *            目标对象的类,必须有public 的 无参数的 construct method.
	 * @param sources
	 *            数据源
	 * @return 数据目标
	 */
	public static <T> Collection convert(Class<T> targetClass,
			Collection sources) {
		if (sources == null || sources.isEmpty()) {
			return null;
		}
		try {
			Collection targets = sources.getClass().newInstance();
			for (Object sourceObject : sources) {
				Object targetObject = targetClass.newInstance();
				BeanUtils.copyProperties(sourceObject, targetObject);
				targets.add(targetObject);
			}
			return targets;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T convert2(Class<T> targetClass,
			HttpServletRequest request, Map<String, String> convertMap) {

		if (convertMap == null || convertMap.isEmpty()) {
			return null;
		}

		try {
			T targetObject = targetClass.newInstance();
			BeanWrapper sourceBw = new BeanWrapperImpl(targetObject);

			for (String key : convertMap.keySet()) {
				String parameter = request.getParameter(key);

				if (StringUtil.isEmpty(parameter)) {
					continue;
				}

				if (request.getMethod().equalsIgnoreCase("GET")) {
					try {
						parameter = URLDecoder.decode(parameter, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						logger.error("convert error:", e);
					}
				}

				if (sourceBw.isWritableProperty(key)) {
					sourceBw.setPropertyValue(key, parameter);
				}

			}

			return targetObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T, K, V> T convertMap2Object(Map<K, V> map, Class<T> clz)
			throws Exception {
		try {
			T obj = clz.newInstance();
			populate(obj, map);
			return obj;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Map > bean properties
	private static <K, V> void populate(Object bean, Map<K, V> properties)
			throws Exception {

		BeanInfo info = Introspector.getBeanInfo(bean.getClass());

		if (info != null) {
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
			for (int i = 0; i < descriptors.length; i++) {
				PropertyDescriptor descriptor = descriptors[i];

				String name = descriptor.getName();
				if (properties.containsKey(name)) {
					Object value = properties.get(name);
					if (value != null) {
						org.apache.commons.beanutils.BeanUtils.setProperty(
								bean, name, value);
					}
				}
			}
		}
	}
}