/**
 *  File: BeanUtil.java
 *  Description:
 *  Copyright © 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-8-20   terry_ma     Create
 *
 */
package com.idealunited.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 */
public class BeanUtil {

	/**
	 * bean to string
	 * 
	 * @param bean
	 * @return key1=value1&key2=value2...
	 */
	public static String bean2string(Object bean) {

		if (null == bean) {
			return null;
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
			PropertyDescriptor[] propertyDescriptors = beanWrapper
					.getPropertyDescriptors();
			if (null != propertyDescriptors && propertyDescriptors.length > 0) {

				for (int i = 0; i < propertyDescriptors.length; i++) {

					String propertyName = propertyDescriptors[i].getName();
					Object propertyValue = beanWrapper
							.getPropertyValue(propertyName);
					if (null != propertyValue) {
						stringBuilder.append(propertyName).append("=")
								.append(propertyValue).append("&");
					}
				}
			}
			return stringBuilder.deleteCharAt(stringBuilder.length() - 1)
					.toString();
		}

	}

	/**
	 * beanToString
	 * 
	 * @param bean
	 * @param propertys
	 *            key的顺序，注意key一定要在bean中存在，要不然会报错
	 * @return key1=value1&key2=value2...
	 * @throws Exception
	 * @author 戴德荣
	 */
	public static String bean2string(Object bean, String[] propertys)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < propertys.length; i++) {
			String key = propertys[i];
			String value = (String) PropertyUtils.getProperty(bean, key);
			sb.append(key + "=" + value == null ? "" : value);
			if (i != propertys.length - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * beanToString
	 * 
	 * @param bean
	 * @param propertys
	 *            key的顺序，注意key一定要在bean中存在，要不然会报错
	 * @return value1&value2...
	 * @throws Exception
	 * @author 戴德荣
	 */
	public static String beanValue2string(Object bean, String[] propertys)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < propertys.length; i++) {
			String key = propertys[i];
			Object value = PropertyUtils.getProperty(bean, key);
			String vs = "";
			if (value != null) {
				vs = value + "";
			}
			sb.append(vs);
			if (i != propertys.length - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * beanToJSON
	 * 
	 * @param bean
	 * @return json字串
	 */
	public static String bean2JSON(Object bean) throws Exception {

		StringBuffer sb = new StringBuffer("{");
		// ---
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
		for (int i = 0; i < pds.length; i++) {

			PropertyDescriptor key = pds[i];
			String keyType = key.getPropertyType().getSimpleName()
					.toLowerCase();
			String types = "integer,string,long,boolean,short,byte";
			if (types.indexOf(keyType) == -1) {
				continue;
			}
			Object value = PropertyUtils.getProperty(bean, key.getName());
			String vs = "\"" + key.getName() + "\":\""
					+ (value == null ? "" : value) + "\"";
			sb.append(vs);
			if (i != pds.length - 1) {
				sb.append(",");
			}
		}
		// --
		sb.append("}");
		return sb.toString();
	}

	/**
	 * bean转Map
	 * @param obj
	 * @param type 1将key转成带下划线的key,0不对key转换
	 * @return
	 */
	public static Map<String, Object> bean2Map(Object obj,String type,String[] ingoreProperty) {  
		  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();
                if(Arrays.asList(ingoreProperty).contains(key)){
                	continue;
                };
                if("1".equals(type)){
                	key = StringUtil.camelToUnderline(key).toUpperCase();
                }
                // 过滤class属性  
                if (!key.equals("class") && !key.equals("CLASS")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    }
}
