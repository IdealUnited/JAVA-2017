package com.idealunited.poss.systemmanager.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.security.authentication.LoginUserAccessFilter;
import com.idealunited.poss.security.model.OpenUrlConfig;
import com.idealunited.poss.security.model.OpenUrlIpGroup;
import com.idealunited.poss.security.service.IOpenUrlConfigService;
import com.idealunited.poss.security.util.IpUtils;

/**
 * 开放的URL管理
 * @Description 
 * @project 	poss-systemmanager
 * @file 		OpenUrlAdminController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-11-12		戴德荣			Create
 */
public class OpenUrlAdminController extends MultiActionController {

	/**
	 * 开放的url的管理页面
	 */
	private String openUrlAdminView;
	
	private IOpenUrlConfigService openUrlConfigService;
	
	

	/**
	 * @param openUrlConfigService the openUrlConfigService to set
	 */
	public void setOpenUrlConfigService(IOpenUrlConfigService openUrlConfigService) {
		this.openUrlConfigService = openUrlConfigService;
	}
	/**
	 * @return the openUrlAdminView
	 */
	public String getOpenUrlAdminView() {
		return openUrlAdminView;
	}
	/**
	 * @param openUrlAdminView the openUrlAdminView to set
	 */
	public void setOpenUrlAdminView(String openUrlAdminView) {
		this.openUrlAdminView = openUrlAdminView;
	}


	/**
	 * 管理URL
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @date 2010-11-12
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginUserAccessFilter loginUserAccessFilter = (LoginUserAccessFilter) 
		WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean("loginUserAccessFilter");
		loginUserAccessFilter.initNotFilterUrls();
		List<String> list = loginUserAccessFilter.getNotFilterUrls();
		Map<String, OpenUrlConfig> configMaps =  loginUserAccessFilter.getOpenUrlConfigs();
		return new ModelAndView(openUrlAdminView).addObject("openUrlList",list).addObject("configMaps",configMaps);
	}
	
	
	/**
	 * 修改ip组的
	 * @param request
	 * @param response
	 * @param ipGroup
	 * @return
	 * @throws Exception 
	 * @date 2011-1-19
	 */
	
	public ModelAndView modfiyIpGroup(HttpServletRequest request,
			HttpServletResponse response,OpenUrlIpGroup ipGroup) throws Exception {
		String result = "S";
		try{
			if(ipGroup.getStartIpLong()>ipGroup.getEndIpLong()){
				result = "开始IP不能大于结束IP";
			}else{
				openUrlConfigService.updateIpGroup(ipGroup);
			}
			
		}catch (Exception e) {
			logger.error("修改时ip配置时报错");
			logger.error(e);
			result = "error,修改时报异常";
		}
		response.setContentType("text/plain;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
		
	}
	/**
	 * 删除ip组
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @date 2011-1-19
	 */
	public ModelAndView removeIpGroup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = "S";
		try{
			String id = ServletRequestUtils.getStringParameter(request, "groupId","");
			if(! id.matches("\\d{1,}")){
				result = "error id Parameter";
			}else{
				boolean updateFlag = openUrlConfigService.removeIpGroup(new Long(id));
				if(! updateFlag){
					result = "update Failed";
				}
			}
			
		}catch (Exception e) {
			logger.error("删除时ip配置时报错");
			logger.error(e);
			result = "error,"+e.getMessage();
		}
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
		
	}
	
	
	/**
	 * 增加 url配置
	 * @param request
	 * @param response
	 * @param urlConfig
	 * @return
	 * @throws Exception 
	 * @date 2011-1-19
	 */
	public ModelAndView addOpenUrlConfig(HttpServletRequest request,
			HttpServletResponse response,OpenUrlConfig urlConfig) throws Exception {
		String result = "S";
		try{
			openUrlConfigService.createOpenUrlConfig(urlConfig);
		}catch (Exception e) {
			logger.error("保存配置时报错");
			logger.error(e);
			result = "error,保存时异常,确保参数都正确";
		}
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
	}
	
	/**
	 * 增加 url ip配置
	 * @param request
	 * @param response
	 * @param ipGroup
	 * @return
	 * @throws Exception 
	 * @date 2011-1-19
	 */
	
	public ModelAndView addConfigIpGroup(HttpServletRequest request,
			HttpServletResponse response,OpenUrlIpGroup ipGroup) throws Exception {
		String result = "S";
		try{
			if(ipGroup.getStartIpLong()>ipGroup.getEndIpLong()){
				result = "开始IP不能大于结束IP";
			}else{
				OpenUrlIpGroup obj = openUrlConfigService.createIpGroup(ipGroup);
				if(obj.getGroupId()!=null){
					result = obj.getGroupId()+"";
				}
			}
			
			
		}catch (Exception e) {
			logger.error("新增ip配置时报错");
			logger.error(e);
			result = "error,新增ip配置时报错";
		}
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
	}
	
	
	/**
	 * 删除url配置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @date 2011-1-18
	 */
	public ModelAndView removeUrlConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = "S";
		try{
			String id = ServletRequestUtils.getStringParameter(request, "configId","");
			if(! id.matches("\\d{1,}")){
				result = "error id Parameter";
			}else{
				boolean updateFlag = openUrlConfigService.removeUrlConfig(new Long(id));
				if(! updateFlag){
					result = "update Failed";
				}
			}
			
		}catch (Exception e) {
			logger.error("删除ip配置时报错");
			logger.error(e);
			result = "error,删除ip配置时报错";
		}
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
	}
	

	/**
	 * 修改url配置
	 * @param request
	 * @param response
	 * @param openUrlConfig
	 * @return
	 * @throws Exception 
	 * @date 2011-1-19
	 */
	public ModelAndView modifyUrlConfig(HttpServletRequest request,
			HttpServletResponse response,OpenUrlConfig openUrlConfig ) throws Exception {
		String result = "S";
		try{
			openUrlConfigService.updateOpenUrlConfig(openUrlConfig);
		}catch (Exception e) {
			logger.error("修改时ip配置时报错");
			logger.error(e);
			result = "error,修改时报异常";
		}
		response.setContentType("text/plain;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		//刷新
		return null;
		
	}
	
}
