 /** @Description 
 * @project 	poss-base
 * @file 		AbstractBaseController.java 
 * Copyright © 2006-2010 pay Corporation. All rights reserved
 * @version     1.0
 * Date				Author			Changes
 * 2010-8-10		Henry.Zeng			Create 
*/
package com.idealunited.controller.common;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.util.WebBindUtils;

/**
 * <p>基类Controller</p>
 * @author Henry.Zeng
 * @since 2010-8-10
 * @see 
 */
public abstract class AbstractBaseController extends MultiActionController {
	
	protected transient Log log = LogFactory.getLog(getClass());
	
	protected transient String success;
	
	protected transient String failure;
	
	protected transient Map<String,String> urlMap;
	/**
	 * <pre>
	 * 通过SpringIOC配置统一的成功结果跳转页面
	 * </pre>
	 * @param success
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * <pre>
	 * 通过SpringIOC配置统一的失败结果跳转页面
	 * </pre>
	 * @param failure
	 */
	public void setFailure(String failure) {
		this.failure = failure;
	}
	/**
	 * <pre>
	 * 通过SpringIOC配置ModelAndView各种结果对应跳转路径的Map
	 * </pre>
	 * @param urlMap
	 */
	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
	/**
	 * 通过一个Key从urlMap里面取得Spring IOC注入配置的值
	 * @param key Map的键
	 * @return String 值

	 */
	protected String URL(String key) {
		if (urlMap == null || urlMap.size() <= 0 || urlMap.get(key) == null) {
			return failure;
		}
		return (String) urlMap.get(key);
	}
	
	/**
	 * 页面request参与与Model对象绑定
	 * @param request
	 * @param object
	 * @param objectName
	 * @param dateFormat
	 * @throws ServletException
	 * @author Henry.Zeng
	 * @see
	 */
	protected void bind(HttpServletRequest request, Object object , String objectName,String dateFormat)  {
		try{
			WebBindUtils.bind(request, object, objectName, true, dateFormat);
		}catch (ServletException e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
