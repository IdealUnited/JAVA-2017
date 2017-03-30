package com.idealunited.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * JsonUtil 提供Json和对象之间的转换。
 * 
 * @author Kingo.Liang
 * 
 */
public class JSonUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(DateUtil.DEFAULT_DATE_FROMAT));	// 设置序列化日期格式为"yyyy-MM-dd HH:mm:ss"
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);	// 设置序列化NULL值的key不输入到JSON字符串
		mapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat(DateUtil.DEFAULT_DATE_FROMAT));	// 设置反序列化日期格式为"yyyy-MM-dd HH:mm:ss"
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
	}

	/**
	 * 把对象转换成Json字符串。
	 * 
	 * @param obj
	 *            需要转换的对象。
	 * @return 转换好的字符串。如果出错会抛出RuntimeException
	 */
	public static String toJSonString(Object obj) {
		StringWriter out = new StringWriter();
		try {
			mapper.writeValue(out, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return out.toString();
	}

	/**
	 * 把Json字符串转换成对象
	 * 
	 * @param <T>
	 *            所要转换的对象类型
	 * @param json
	 *            Json字符串
	 * @param clazz
	 *            转换对象有类型
	 * @return 转换好的对象，如果出错会抛出RuntimeException
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}

	public static Collection json2Collection(String json, Class collectionClz,
			Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toList(jsonArray, valueClz);
	}

	// 将JSON转换成POJO,其中beanClz为POJO的Class
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
	}

	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * 将json格式的字符串解析成Map对象 
	 */
	public static HashMap<String, String> toHashMap(Object object) {
		HashMap<String, String> data = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator<?> it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object obc = jsonObject.get(key);
			String value="";
			if(value!=null){
				value=(String)obc;
			}
			data.put(key, value);
		}
		return data;
	}

}
