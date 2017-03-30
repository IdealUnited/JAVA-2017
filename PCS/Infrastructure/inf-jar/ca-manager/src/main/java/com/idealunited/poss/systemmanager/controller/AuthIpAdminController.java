package com.idealunited.poss.systemmanager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessAuthority;
import com.idealunited.poss.security.service.IAccessAuthorityService;
import com.idealunited.poss.security.util.IpUtils;
import com.idealunited.util.JSonUtil;

/**
 * 开放的URL管理
 * @Description 
 * @project 	poss-systemmanager
 * @file 		AuthIpAdminController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2011-8-1			戴德荣			Create
 */
public class AuthIpAdminController extends MultiActionController {
	
	
	private IAccessAuthorityService accessAuthorityService;

	private String indexView;
	private String editView;
	private String listView;
	private String detailView;
	
	/**
	 * @return the indexView
	 */
	public String getIndexView() {
		return indexView;
	}

	/**
	 * @param indexView the indexView to set
	 */
	public void setIndexView(String indexView) {
		this.indexView = indexView;
	}

	/**
	 * @return the editView
	 */
	public String getEditView() {
		return editView;
	}

	/**
	 * @param editView the editView to set
	 */
	public void setEditView(String editView) {
		this.editView = editView;
	}

	/**
	 * @return the listView
	 */
	public String getListView() {
		return listView;
	}

	/**
	 * @param listView the listView to set
	 */
	public void setListView(String listView) {
		this.listView = listView;
	}

	/**
	 * @return the detailView
	 */
	public String getDetailView() {
		return detailView;
	}

	/**
	 * @param detailView the detailView to set
	 */
	public void setDetailView(String detailView) {
		this.detailView = detailView;
	}

	
	
	

	/**
	 * @param accessAuthorityService the accessAuthorityService to set
	 */
	public void setAccessAuthorityService(
			IAccessAuthorityService accessAuthorityService) {
		this.accessAuthorityService = accessAuthorityService;
	}

	/**
	 * 授权查询页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @date 2010-11-12
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(indexView);
	}
	/**
	 * 搜索列
	 * @param request
	 * @param response
	 * @param accessAuthority
	 * @return
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response,AccessAuthority accessAuthority){
		Page<AccessAuthority> paramPage = PageUtils.getPage(request);
		Page<AccessAuthority> page =  accessAuthorityService.search(paramPage, accessAuthority);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		return new ModelAndView(listView,map);
	}
	
	/**
	 * 新增授权ip
	 * @param request
	 * @param response
	 * @param accessAuthority
	 * @return 
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView addAuthIp(HttpServletRequest request,
			HttpServletResponse response,AccessAuthority accessAuthority) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		boolean isok = false;
		String exceptionMsg = "";
		try{
			 if(IpUtils.isLocalAreaIp(accessAuthority.getAuthIp())){
				 exceptionMsg = "局域网ip不需要授权";
			 }else{
				  accessAuthorityService.createAccessAuthority(accessAuthority);
				  isok =  true;
			 }
			
		}catch (Exception e) {
			isok = false;
			exceptionMsg = "后台操作异常";
			logger.error("新增异常", e);
			e.printStackTrace();
		}
		response.getWriter().write(isok?"S":"新增失败，"+exceptionMsg);
		return null;
		
	}
	/**
	 * 更新授权
	 * @param request
	 * @param response
	 * @return 
	 * @throws IOException
	 */
	public ModelAndView updateAuthIp(HttpServletRequest request,
			HttpServletResponse response,AccessAuthority accessAuthority) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		boolean isok = false;
		String exceptionMsg = "";
		try{
			 if(IpUtils.isLocalAreaIp(accessAuthority.getAuthIp())){
				 exceptionMsg = "局域网ip不需要授权";
			 }else{
			  accessAuthorityService.updateAccessAuthority(accessAuthority);
			  isok =  true;
			 }
		}catch (Exception e) {
			isok = false;
			exceptionMsg = "后台操作异常";
			logger.error("更新异常", e);
			e.printStackTrace();
		}
		response.getWriter().write(isok?"S":"更新失败，"+exceptionMsg);
		return null;
	}
	/**
	 * 得到json对象
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAuthIpJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		
		Long id  = ServletRequestUtils.getLongParameter(request, "authId",-1);
		AccessAuthority au = null;
		try{
			if(id>0){
				 au  =   accessAuthorityService.getAccessAuthorityById(id);
			}
		}catch (Exception e) {
			logger.error("更新异常", e);
			e.printStackTrace();
		}
		String json = JSonUtil.toJSonString(au);
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 删除授权
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteAuthIp(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		
		Long id  = ServletRequestUtils.getLongParameter(request, "authId",-1);
		boolean isOk = false;
		String exceptionMsg = "";
		try{
			if(id>0){
				 isOk  =   accessAuthorityService.deleteAccessAuthority(id) ;
				 if(! isOk){
					 exceptionMsg = "对应的数据不存在或是已被删除。"; 
				 }
			}
		}catch (Exception e) {
			logger.error("删除异常", e);
			exceptionMsg = "删除出现异常，"+e.getClass().getSimpleName();
			e.printStackTrace();
		}
		response.getWriter().write(isOk ? "S":"更新失败，"+exceptionMsg);
		return null;
	}
	
	
}
