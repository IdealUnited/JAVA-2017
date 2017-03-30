package com.idealunited.poss.systemmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.service.IRoleService;

/**
 * 批量处理角色
 * @Description 
 * @project 	poss-systemmanager
 * @file 		BatchRoleForUserController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-11		Volcano.Wu			Create
 */
public class BatchRoleForUserController extends MultiActionController{
	
	private Log logger = LogFactory.getLog(getClass());
	private String toView;
	private IRoleService roleService;
	

	public void setToView(String toView) {
		this.toView = toView;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	//用户列表 
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		Map<String,List<UserFormBean>> model = new HashMap<String,List<UserFormBean>>();

		
		//model.put("users", "");
		return new ModelAndView(toView,model);
	}
}
