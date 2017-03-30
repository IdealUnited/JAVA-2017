/**
 *  File: MapUtil.java
 *  Description:
 *  Copyright © 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-8-20   terry_ma     Create
 *
 */
package com.idealunited.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 */
public class MapUtil {

	private static Log logger = LogFactory.getLog(MapUtil.class);

	/**
	 * 
	 * @param map
	 * @return key1=value1&key2=value2...
	 */
	public static String map2string(Map map) {

		if (null == map || map.isEmpty()) {
			return null;
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			Iterator iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				Object value = map.get(key);
				if (null != value && !(value instanceof Class)
						&& !(value instanceof List)) {
					stringBuilder.append(key).append("=").append(value)
							.append("&");
				}
			}
			if (stringBuilder.length() > 1
					&& stringBuilder.toString().endsWith("&")) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1)
						.toString();
			}

			return stringBuilder.toString();
		}
	}

	/**
	 * 将对象转换为String，key=value&key=value
	 * 
	 * @param bean
	 * @return
	 */
	public static String bean2String(Object bean) {

		StringBuilder sb = new StringBuilder();

		BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
		PropertyDescriptor[] propertyDescriptors = beanWrapper
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String propertyName = property.getDisplayName();
			Object value = beanWrapper.getPropertyValue(propertyName);
			if ("class".equals(propertyName)) {
				continue;
			}
			sb.append("&");
			sb.append(propertyName);
			sb.append("=");
			sb.append(value);
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static Map string2map(String string) {

		if (logger.isInfoEnabled()) {
			logger.info("string2map tostring:" + string);
		}

		Map<String, String> paraMap = null;
		try {
			if (!StringUtil.isEmpty(string)) {
				paraMap = new HashMap<String, String>();
				String[] paras = string.split("&");
				if (null != paras && paras.length > 0) {
					for (int i = 0; i < paras.length; i++) {
						String tempStr = paras[i];
						int splitIndex = tempStr.indexOf("=");
						paraMap.put(
								tempStr.substring(0, splitIndex),
								tempStr.substring(splitIndex + 1,
										tempStr.length()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("string2map", e);
		}

		return paraMap;
	}

	public static <T> Map bean2map(T obj) {
		Map map = new HashMap();

		if (null == obj) {
			return map;
		}
		try {
			BeanWrapper bean = new BeanWrapperImpl(obj);

			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : properties) {
				String key = propertyDescriptor.getDisplayName();
				Object value = bean.getPropertyValue(key);
				if (null != value && !"class".equals(key)) {
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return map;
	}

	public static <T> Map<String, T> list2map(List<T> list, String keyProperty) {
		Map map = new HashMap();
		if (null != list) {
			for (T t : list) {
				BeanWrapper bean = new BeanWrapperImpl(t);
				map.put(bean.getPropertyValue(keyProperty), t);
			}
		}
		return map;
	}

	public static <T> T map2Object(Class<T> targetClass, Map map) {

		try {
			T targetObject = targetClass.newInstance();

			if (null == map) {
				return targetObject;
			}

			BeanWrapper bean = new BeanWrapperImpl(targetObject);
			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				String key = properties[i].getDisplayName();
				Object value = map.get(key);
				if (null != value && bean.isWritableProperty(key)) {

					Class propertyType = bean.getPropertyType(key);
					if (propertyType.equals(String.class)) {
						if (!StringUtil.isEmpty(String.valueOf(value))) {
							bean.setPropertyValue(key, String.valueOf(value));
						}
					} else if (propertyType.equals(BigDecimal.class)) {

						if (!StringUtil.isEmpty(String.valueOf(value))) {
							bean.setPropertyValue(key, new BigDecimal(value
									+ ""));
						}
					} else if (propertyType.equals(Long.class)) {

						if (!StringUtil.isEmpty(String.valueOf(value))) {
							bean.setPropertyValue(key, Long.valueOf(value + ""));
						}
					} else if (propertyType.equals(Date.class)) {
						if (value instanceof Long) {
							bean.setPropertyValue(key,
									DateUtil.parse(((Long) value).longValue()));
						} else if (value instanceof String) {
							String val = String.valueOf(value);
							switch (val.length()) {
							case 19:
								bean.setPropertyValue(key, DateUtil.parse(
										DateUtil.DEFAULT_DATE_FROMAT,val));
								break;
							case 10:
								bean.setPropertyValue(key, DateUtil.parse(
										DateUtil.SIMPLE_DATE_FROMAT,val));
								break;
							case 14:
								bean.setPropertyValue(key, DateUtil.parse(
										DateUtil.PATTERN1,val));
								break;
							case 8:
								bean.setPropertyValue(key, DateUtil.parse(
										DateUtil.PATTERN,val));
								break;
							default:
								break;
							}
						} else {
							bean.setPropertyValue(key, value);
						}
					} else {
						bean.setPropertyValue(key, value);
					}

				}
			}
			return targetObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> T map2Object(Class<T> targetClass, Map map,
			String... excludeKey) {
		try {
			T targetObject = targetClass.newInstance();

			if (null == map) {
				return targetObject;
			}

			BeanWrapper bean = new BeanWrapperImpl(targetObject);
			PropertyDescriptor[] properties = bean.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				String key = properties[i].getDisplayName();
				Object value = map.get(key);
				if(!Arrays.asList(excludeKey).contains(key)){
					if (null != value && bean.isWritableProperty(key)) {
						Class propertyType = bean.getPropertyType(key);
						if (propertyType.equals(String.class)) {
							if (!StringUtil.isEmpty(String.valueOf(value))) {
								bean.setPropertyValue(key, String.valueOf(value));
							}
						} else if (propertyType.equals(BigDecimal.class)) {

							if (!StringUtil.isEmpty(String.valueOf(value))) {
								bean.setPropertyValue(key, new BigDecimal(value
										+ ""));
							}
						} else if (propertyType.equals(Long.class)) {

							if (!StringUtil.isEmpty(String.valueOf(value))) {
								bean.setPropertyValue(key, Long.valueOf(value + ""));
							}
						} else if (propertyType.equals(Date.class)) {
							if (value instanceof Long) {
								bean.setPropertyValue(key,
										DateUtil.parse(((Long) value).longValue()));
							} else if (value instanceof String) {
								String val = String.valueOf(value);
								switch (val.length()) {
								case 19:
									bean.setPropertyValue(key, DateUtil.parse(
											DateUtil.DEFAULT_DATE_FROMAT,val));
									break;
								case 10:
									bean.setPropertyValue(key, DateUtil.parse(
											DateUtil.SIMPLE_DATE_FROMAT,val));
									break;
								case 14:
									bean.setPropertyValue(key, DateUtil.parse(
											DateUtil.PATTERN1,val));
									break;
								case 8:
									bean.setPropertyValue(key, DateUtil.parse(
											DateUtil.PATTERN,val));
									break;
								default:
									break;
								}
							} else {
								bean.setPropertyValue(key, value);
							}
						} else {
							bean.setPropertyValue(key, value);
						}

					}
				}
			}
			return targetObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List map2List(Class<T> targetClass, List<Map> listMap) {

		if (null == listMap || listMap.isEmpty()) {
			return null;
		}
		List<T> resultList = new ArrayList<T>();
		try {
			for (Map map : listMap) {
				resultList.add(map2Object(targetClass, map));
			}

			return resultList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	
	public static List<Map> map2List(List list) {

		if (null == list || list.isEmpty()) {
			return null;
		}
		List<Map> resultList = new ArrayList<Map>();
		try {
			for (Object obj : list) {
				resultList.add(bean2map(obj));
			}

			return resultList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static Map xml2map(final String xml) {
		Map map = new HashMap();

		SAXReader reader = new SAXReader();
		try {
			Document document;
			document = reader.read(new ByteArrayInputStream(xml
					.getBytes("UTF-8")));
			Element rootElm = document.getRootElement();
			parseXml2Map(rootElm, map);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}
	
	public static Map convertParameterMapToNomalMap(Map paramtersMap) {
		if(paramtersMap==null){
			return null;
		}
	    // 返回值Map
	    Map returnMap = new HashMap();
	    Iterator entries = paramtersMap.entrySet().iterator();
	    Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else if(valueObj instanceof List){
	        	List values = (List)valueObj;
	            for(int i=0;i<values.size();i++){
	                value = values.get(i) + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        returnMap.put(name, value);
	    }
	    return returnMap;
	}

	private static void parseXml2Map(Element ele, Map map) {
		List<Element> list = ele.elements();
		if (null == list || list.isEmpty()) {
			String label = ele.getName();
			String text = ele.getTextTrim();
			map.put(label, text);
		} else {
			for (Element e : list) {
				parseXml2Map(e, map);
			}
		}
	}

	public static void main(String[] args) {
		Map map = xml2map("<?xml version=\"1.0\" encoding=\"utf-8\" ?><LoginUserResponseType xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><LoginSuccess>true</LoginSuccess><User><Userid>100000</Userid><Name>xxxxxx</Name><ApiToken>xxxxxx </ApiToken><Level>xx</Level></User></LoginUserResponseType>");
		System.out.println(map);
	}
}
