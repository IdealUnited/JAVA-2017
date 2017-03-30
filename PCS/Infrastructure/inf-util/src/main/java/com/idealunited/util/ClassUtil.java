/**
 * 
 */
package com.idealunited.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

public class ClassUtil {
    private static Map <Class, List> beanInfos = new HashMap <Class, List> ();
    
    /**
     * 
     */
    public ClassUtil() {
        super();
    }
    
    public static List getPropertyDesc(Class clazz) {
        List temp = beanInfos.get(clazz);
        if (null != temp) {
            //return temp;
        }
        List <BeanInfo> beanInfo = new ArrayList <BeanInfo> ();
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(clazz);
        int size = descriptors.length;
        for (int i=0; i<size; i++) {
            PropertyDescriptor descriptor = descriptors[i];
            String name = descriptor.getName();
            /*if ("primaryKey".equals(name)) {
                continue;
            }*/
            BeanInfo item = new BeanInfo();
            item.setProperty(name);
            Method readMethod = descriptor.getReadMethod();
            if (null != readMethod) {
                item.setGetMethodName(readMethod.getName());
                item.setReadMethod(readMethod);
            } else {
                continue;
            }
            Method writeMethod = descriptor.getWriteMethod();
            if (null != writeMethod) {
                item.setSetMethodName(writeMethod.getName());
                item.setWriteMethod(writeMethod);
            } else {
                continue;
            }
            Class propertyType = descriptor.getPropertyType();
            item.setParameterType(propertyType.getSimpleName());
            item.setPropertyType(propertyType);
            if (!propertyType.isPrimitive()
                    && Integer.class != propertyType
                    && Double.class != propertyType
                    && Long.class != propertyType
                    && String.class != propertyType) {
                item.setImportType(propertyType.getName());
            }
            beanInfo.add(item);
        }
        beanInfos.put(clazz, beanInfo);
        return beanInfo;
    }
    
    public static Map getMapPropertyDesc(Class clazz) {
        List temp = beanInfos.get(clazz);
        if (null != temp) {
            //return temp;
        }
        Map <String, BeanInfo> beanInfo = new HashMap <String, BeanInfo> ();
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(clazz);
        int size = descriptors.length;
        for (int i=0; i<size; i++) {
            PropertyDescriptor descriptor = descriptors[i];
            String name = descriptor.getName();
            /*if ("primaryKey".equals(name)) {
                continue;
            }*/
            BeanInfo item = new BeanInfo();
            item.setProperty(name);
            Method readMethod = descriptor.getReadMethod();
            if (null != readMethod) {
                item.setGetMethodName(readMethod.getName());
                item.setReadMethod(readMethod);
            } else {
                continue;
            }
            Method writeMethod = descriptor.getWriteMethod();
            if (null != writeMethod) {
                item.setSetMethodName(writeMethod.getName());
                item.setWriteMethod(writeMethod);
            } else {
                continue;
            }
            Class propertyType = descriptor.getPropertyType();
            item.setParameterType(propertyType.getSimpleName());
            item.setPropertyType(propertyType);
            if (!propertyType.isPrimitive()
                    && Integer.class != propertyType
                    && Double.class != propertyType
                    && Long.class != propertyType
                    && String.class != propertyType) {
                item.setImportType(propertyType.getName());
            }
            beanInfo.put(name, item);
        }
        return beanInfo;
    }
    
    public static Object mock(Class clazz) {
        try {
            Object obj = clazz.newInstance();
            PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(clazz);
            Map <String, PropertyDescriptor> beanInfos= new HashMap <String, PropertyDescriptor> ();
            int size = descriptors.length;
            for (int i=0; i<size; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                String property = descriptor.getName();
                Method writeMethod = descriptor.getWriteMethod();
                Method readMethod = descriptor.getReadMethod();
                if (null==writeMethod || null==property || "".equals(property)) {
                    continue;
                }
                if (null!=readMethod && null!=writeMethod) {
                    beanInfos.put(property, descriptor);
                }
                Object value = getMockValue(readMethod.getReturnType());
                writeMethod.invoke(obj, new Object[]{value});
            }
            /*if (obj instanceof Model) {
                List pk = ((Model)obj).getPrimaryKeyFields();
                getPkMockValue(beanInfos, obj, pk);
            }
            if (obj instanceof Dto) {
                List pk = ((Dto)obj).getPrimaryKeys();
                getPkMockValue(beanInfos, obj, pk);
            }*/
            return obj;
        } catch (Exception e) {
            e.printStackTrace();            
        }
        return null;
    }
    
    private static Object getMockValue(Class clazz) {
        if (int.class==clazz || long.class==clazz) {
            return 1;
        }
        if (Integer.class == clazz) {
            return new Integer(2);
        }
        if (Long.class == clazz) {
            return new Long(3);
        }
        if (double.class == clazz) {
            return 4;
        }
        if (Double.class == clazz) {
            return new Double(5);
        }
        if (String.class == clazz) {
            return "6";
        }
        if (Date.class == clazz) {
            return new Date();
        }
        if (Timestamp.class == clazz) {
            return new Timestamp(new Date().getTime());
        }
        return null;
    }
    
    private static void getPkMockValue(Map beanInfos, Object obj, List pk) {
        Iterator it = pk.iterator();
        Object[] objs = new Object[pk.size()];
        int i = 0;
        while (it.hasNext()) {
            String name = (String)it.next();
            PropertyDescriptor descriptor = (PropertyDescriptor)beanInfos.get(name);
            Method readMethod = descriptor.getReadMethod();
            Object value = getMockValue(readMethod.getReturnType());
            objs[i] = value;
            i++;
        }
        try {
            Method method = obj.getClass().getMethod("getId", new Class[]{Object.class});
            method.invoke(obj, new Object[]{objs});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Object convert(String value, Class type) {
        if (null == value) {
            return null;
        }
        if (String.class == type) {
            return value;
        }
        if ("".equals(value)) {
            return null;
        }
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        if (int.class == type) {
            return Integer.parseInt(value);
        }
        if (Long.class == type) {
            return Long.valueOf(value);
        }
        if (int.class == type) {
            return Long.parseLong(value);
        }
        if (Double.class == type) {
            return Long.valueOf(value);
        }
        if (int.class == type) {
            return Double.parseDouble(value);
        }
        if (Date.class == type) {
            Date temp = null;
            try {
                temp = DateUtil.parse("yyyy-MM-dd HH:mm:ss", value);
            } catch (RuntimeException e) {
            }
            if (null == temp) {
                temp = DateUtil.parse("yyyy-MM-dd", value);
            }
            return temp;
        }
        if (Timestamp.class == type) {
            return new Timestamp(((Date)convert(value, Date.class)).getTime());
        }
        return null;
    }
    
    /**
     * object to string.
     * @param obj Object.
     * @return String
     */
    public static String objectToString(final Object obj) {
    	if (null == obj) {
    		return "NULL";
    	}
    	if (obj.getClass().isPrimitive()) {
    		return obj.toString();
    	}
    	StringBuffer buf = new StringBuffer();
    	List <BeanInfo> beanInfo = getPropertyDesc(obj.getClass());
    	if (null==beanInfo || 0==beanInfo.size()) {
    		return obj.toString();
    	}
    	Iterator <BeanInfo> it = beanInfo.iterator();    	
    	while (it.hasNext()) {
    		BeanInfo bean = it.next();
    		buf.append("[").append(bean.getProperty()).append("=");
    		try {
				Object value = bean.getReadMethod().invoke(obj, new Object[]{});
				if (value instanceof String) {
					buf.append(value.toString());
				} else {
					buf.append(objectToString(value));
				}
			} catch (Exception e) {
				buf.append("=unknown");
			}
			buf.append("];");
    	}
    	return buf.toString();
    }
    
    public static Map <String, String> getObjectProperties(final Object obj) {
    	Map result = new HashMap();
    	if (null == obj) {
    		return null;
    	}
    	List <BeanInfo> beanInfo = getPropertyDesc(obj.getClass());
    	if (null==beanInfo || 0==beanInfo.size()) {
    		result.put("", obj.toString());
    		return result;
    	}
    	Iterator <BeanInfo> it = beanInfo.iterator();    	
    	while (it.hasNext()) {
    		BeanInfo bean = it.next();
    		try {
				Object value = bean.getReadMethod().invoke(obj, new Object[]{});
				if (null == value) {
					result.put(bean.getProperty(), "unknown");
					continue;
				}
				Class clazz = value.getClass();
				if (clazz.isPrimitive() || clazz==String.class || clazz==Integer.class
						|| clazz==Long.class || clazz==Double.class
						|| clazz==Date.class || clazz==Boolean.class || clazz==Timestamp.class) {
					result.put(bean.getProperty(), value.toString());
				} else if (value instanceof Map || value instanceof List || value instanceof Set) {
					result.put(bean.getProperty(), value.toString());
				} else {
					String property = bean.getProperty();
					Map temp = getObjectProperties(value);
					Iterator tempId = temp.keySet().iterator();
					while (it.hasNext()) {
						String tempKey = (String) tempId.next();
						String tempValue = (String) temp.get(tempKey);
						result.put(property + ":" + tempKey, tempValue);
					}
				}
			} catch (Exception e) {
				result.put(bean.getProperty(), "unknown");
			}
    	}
    	return result;
    }
}
