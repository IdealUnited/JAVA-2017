 /** @Description 
 * @project 	poss-reconcile
 * @file 		WebBindUtils.java 
 * Copyright © 2004-2013 pay.com . All rights reserved. 版权所有
 * @version     1.0
 * Date				Author			Changes
 * 2010-7-27		Henry.Zeng			Create 
*/
package com.idealunited.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
/**
 * <p>绑定页面对象到DTO</p>
 * @author Henry.Zeng
 * @since 2010-7-27
 * @see 
 */
public class WebBindUtils{
	
	
	 private final static String defaultDataFormat = "yyyy-MM-dd";
	
	 /**
	  * <p>用于绑定页面参数到对应的Model</p>
	  * @param request 
	  * @param object 要绑定到的对象,绑定之前需先new出来
	  * @param objectName 一般填写为对象名字的字符串
	  * @param defaultInitializtion 是否需要注册日期,金额等 
	  * @param dateFormat 日期绑定的格式默认是"yyyy-MM-dd"可以为null
	  * @throws ServletException
	  * @author Henry.Zeng
	  * @see
	  */
	  public static void bind(ServletRequest request, final Object object, final String objectName,
		      final boolean defaultInitializtion, final String dateFormat) throws ServletException {
		  	if(null == object ) return ;
		    ServletRequestDataBinder binder = new ServletRequestDataBinder(object, objectName);
		    if (defaultInitializtion) {
		      //1、注册日期
		      binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor
		    		  	(new SimpleDateFormat(dateFormat==null?defaultDataFormat:dateFormat), true));
		      //2、注册Long
		      DecimalFormat df = new DecimalFormat();
		      df.setGroupingUsed(false);
		      binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, df, true));
		      //3、注册Integer
		      DecimalFormat it = new DecimalFormat();
		      it.setGroupingUsed(false);
		      binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, it, true));
		      //4、注册BigDecimal
		      DecimalFormat bg = new DecimalFormat();
		      bg.setMaximumFractionDigits(8);
		      binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, bg, true));
//		      defaultPropertyEditors(binder);
		    }
		    binder.bind(request);
		  }
	  	  
}
