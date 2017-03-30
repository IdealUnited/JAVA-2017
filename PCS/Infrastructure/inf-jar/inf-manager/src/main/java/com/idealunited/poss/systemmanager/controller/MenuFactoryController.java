package com.idealunited.poss.systemmanager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.systemmanager.formbean.Menu;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;

/**
 * 主页菜单
 * @Description
 * @project poss-systemmanager
 * @file MenuFactoryController.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved. Date
 *          Author Changes 2010-7-29 Volcano.Wu Create
 */
public class MenuFactoryController extends MultiActionController {

	private Log logger = LogFactory.getLog(getClass());

	private String headView;
	private String leftView;
	private String bodyView;
	private IMenuFactoryService menuFactoryService;
	
	public void setHeadView(String headView) {
		this.headView = headView;
	}

	public void setLeftView(String leftView) {
		this.leftView = leftView;
	}
	
	public void setBodyView(String bodyView) {
		this.bodyView = bodyView;
	}

	public void setMenuFactoryService(IMenuFactoryService menuFactoryService) {
		this.menuFactoryService = menuFactoryService;
	}

	// head菜单
	public ModelAndView head(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		Map<String,List<Menu>> model = new HashMap<String,List<Menu>>();
		List<Menu> headMenu = menuFactoryService.getHeadMenu();
		model.put("headMenu", headMenu);
		res.setCharacterEncoding("UTF-8");
		String menuCode = headMenu.get(headMenu.size() - 1).getCode();
		//model.put("menuCode", headMenu.)
		return new ModelAndView(headView, model).addObject("menuCode", menuCode);
	}
	
	// left菜单
	public ModelAndView left(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		String code = req.getParameter("menuCode");
		
		Map<String,List<Menu>> model = new HashMap<String,List<Menu>>();
		List<Menu> leftMenu = menuFactoryService.getSonMenuList(code);
		model.put("leftMenu", leftMenu);
		res.setCharacterEncoding("UTF-8");
		return new ModelAndView(leftView, model);
	}
	
	// left菜单
	public ModelAndView body(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		/*String code = req.getParameter("menuCode");
		
		Map<String,List<Menu>> model = new HashMap<String,List<Menu>>();
		List<Menu> leftMenu = menuFactoryService.getSonMenuList(code);
		model.put("leftMenu", leftMenu);
		res.setCharacterEncoding("UTF-8");*/
		return new ModelAndView(bodyView);
	}

}
