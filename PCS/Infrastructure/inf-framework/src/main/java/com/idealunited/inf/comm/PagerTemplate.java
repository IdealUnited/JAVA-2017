package com.idealunited.inf.comm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.util.StringUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 获取当前请求的requestUrl 给前台分页调用
 * @author zengjin
 * @date 2010-8-5
 * @param 
 */
public class PagerTemplate implements TemplateMethodModel {
	private static Log logger = LogFactory.getLog(PagerTemplate.class);
    private static Map<String, String> transferMaps = new HashMap<String, String>();
    private  static ThreadLocal <HttpServletRequest> requestThreadLocal = new ThreadLocal <HttpServletRequest>();  
    static {
        transferMaps.put("%3c", "&lt;");
        transferMaps.put("<", "&lt;");
        transferMaps.put("\"", "&quot;");
        transferMaps.put("%22", "&quot;");
        transferMaps.put("'", "&#39;");
        transferMaps.put("%27", "&apos;");
        transferMaps.put(">", "&#62;");
    }
    
    public Object exec(List args) throws TemplateModelException {
    	HttpServletRequest request=(HttpServletRequest)requestThreadLocal.get();
        StringBuffer queryTmp=new StringBuffer(request.getRequestURI()==null?"/home":request.getRequestURI());
       
        StringBuffer queryString=new StringBuffer();
        Map map=request.getParameterMap();
        Iterator it = map.keySet().iterator();
        String key="";
        String keyValue="";
        //int k=0;
        while (it.hasNext()) {
            key= (String)it.next();
            if("pager_offset".equals(key) || "menuId".equals(key))
                continue;
            keyValue=map.get(key) ==null?"":((String[])map.get(key))[0];
            if("".equals(keyValue)){
                continue;
            }
            if("method".equals(key)){
            	queryTmp.append("?");
            	queryTmp.append(transfer(key)).append("=");
            	queryTmp.append(transfer(keyValue));
            	queryTmp.append("&");
            }else{
                queryString.append(transfer(key));
                queryString.append("=");
                queryString.append(transfer(keyValue));
                queryString.append("&");
            }
            //k++;
        }
       
        if(queryTmp.indexOf("?")>0){
        	queryTmp.append(queryString);
        }else{
        	queryTmp.append("?");   
        	queryTmp.append(queryString);
        }        
//        if(k==0 ){
//        	queryTmp.append("?");
//        }
        queryTmp.append("pager_offset=");
        
        return queryTmp.toString();
    }
    
  
    
    private String transfer(String parameter) {

        if (!StringUtil.isEmpty(parameter)) {
            String result = parameter;
            for (String key : transferMaps.keySet()) {
                result = result.replaceAll(key, transferMaps.get(key));
            }
            
            if (isContainsChinese(result)) {
                try {
                	result = URLEncoder.encode(result, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("URLEncoder.encode " + result + " throws error :" + e);
                }
            }
            return result;
        }
        return parameter;
    }
    
    public static boolean isContainsChinese(String s){
    	Pattern p =Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = p.matcher(s);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
