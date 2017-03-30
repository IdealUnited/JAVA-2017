package com.idealunited.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebUrlUtil {

	/**
	 * String queryString = "a=1&b=2&c=3&d=4";==>map.put("a",1);... 
	 * @return Map<String,String>
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String,String> urlQueryStringToMap(String queryString) throws UnsupportedEncodingException{
	    
		queryString = URLDecoder.decode(queryString,"UTF-8");
		queryString = queryString.replaceAll("%3D", "=");
		queryString = queryString.replaceAll("%26", "&");
		
		System.out.println(queryString);
        String[] queryStringSplit = queryString.split("&");
        Map<String,String> queryStringMap =
                new LinkedHashMap<String,String>(queryStringSplit.length);
        for (String qs : queryStringSplit) {
            String[] queryStringParam = (qs).split("=");
            String key = queryStringParam[0];
            if(queryStringParam.length==1){
            	queryStringMap.put(key, "");
            }else{
            	 queryStringMap.put(key, queryStringParam[1]);
            }
        }
        return queryStringMap;
	}
	
	/**
	 * 把map参数变成 a=1&b=2&c=3这种url形式
	 * @param map Map 
	 * @return url
	 */
	public static String mapToUrl (Map<String, String> map){
		StringBuffer sb = new StringBuffer("");
		Iterator<String> keyIt = map.keySet().iterator();
		int indx = 0;
		int len = map.size();
		while(keyIt.hasNext()){
			indx++;
			String key = keyIt.next();
			sb.append("");
				String url  = key+"="+map.get(key);
				sb.append(url);
				if(indx!=len){
					sb.append("&");
				}
		}
		return sb.toString();
	}
}
