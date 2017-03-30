package com.idealunited.poss.systemmanager.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.systemmanager.formbean.MenuFormBean;
import com.idealunited.poss.systemmanager.formbean.UserSearchFormBean;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;
import com.idealunited.poss.systemmanager.service.IMenuService;
import com.idealunited.util.StringUtil;

/**
 * 菜单管理
 * @Description 
 * @project 	poss-systemmanager
 * @file 		MenuController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-3		Volcano.Wu			Create
 */
public class MenuController extends MultiActionController {

	private IMenuFactoryService menuFactoryService;
	private IMenuService possMenuService;

	private String toView;

	//注入service
	public void setMenuFactoryService(IMenuFactoryService menuFactoryService) {
		this.menuFactoryService = menuFactoryService;
	}

	public void setPossMenuService(IMenuService possMenuService) {
		this.possMenuService = possMenuService;
	}

	//注入view
	public void setToView(String toView) {
		this.toView = toView;
	}

//	/** 老版本 2012-6-1前版本
//	 * 初始进入 显示菜单
//	 * @param request
//	 * @param response
//	 * @param userSearchFormBean
//	 * @return
//	 * @throws Exception
//	 */
//	public ModelAndView index(HttpServletRequest request,
//			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
//			throws Exception {
//
//		Map<String, String> model = new HashMap<String, String>();
//		String xtree = menuFactoryService.getxtreeScriptToStr();
//		model.put("xtreeStr", xtree);
//		return new ModelAndView(toView, model);
//	}
	
	/**
	 * 
	 * 2012-6-1后版本
	 * DDR 2012-6-1修改版本
	 * 初始进入 显示菜单
	 * @param request
	 * @param response
	 * @param userSearchFormBean
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
			throws Exception {

		Map<String, Object> model =  new HashMap<String, Object>();
		List<ResMenu> treeMenuList = menuFactoryService.queryMenuTree();
		model.put("treeMenuList", treeMenuList);
		return new ModelAndView(toView, model);
	}
	
	/**
	 * 删除菜单节点
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		StringBuffer results = new StringBuffer();
		String id = request.getParameter("id");
		List<MenuFormBean> mfbList = menuFactoryService.queryMenuByPerentKy(id);
		if(0 < mfbList.size()){
			results.append("不能删除！菜单节点下有子菜单节点！");
		}else{
			possMenuService.dropMenuById(id);
			results.append("S");
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(results.toString());
		out.flush();
		out.close();
		return null;
	}
	
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response,MenuFormBean menuFormBean)throws Exception {
		
		
		ResMenu menu = possMenuService.findById(menuFormBean.getResKy());
		menu.setCode(menuFormBean.getCode());
		menu.setName(menuFormBean.getName());
		menu.setParent(Long.valueOf(menuFormBean.getParent()));
		menu.setImage(menuFormBean.getImage());
		menu.setPosition(Long.valueOf(menuFormBean.getPosition()));
		menu.setUrl(menuFormBean.getUrl());
		menu.setAuthorityFlag(menuFormBean.getAuthorityFlag());
		
		boolean isSuccess = possMenuService.updateMenu(menu);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(isSuccess?"S":"菜单修改失败");
		return null;
	}
	
	
	
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response,MenuFormBean menuFormBean)
			throws Exception {	
		
		ResMenu menu = new ResMenu();
		menu.setCode(menuFormBean.getCode());
		menu.setName(menuFormBean.getName());
		menu.setParent(Long.valueOf(menuFormBean.getParent()));
		menu.setImage(menuFormBean.getImage());
		menu.setPosition(Long.valueOf(menuFormBean.getPosition()));
		menu.setUrl(menuFormBean.getUrl());
		menu.setStatus(1);
		menu.setAuthorityFlag(menuFormBean.getAuthorityFlag());
		Long id = possMenuService.insertMenu(menu);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(id>-1?(id+""):"菜单增加失败");
	
		return null;
	}
	
	
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
			throws Exception {
		
		String id = request.getParameter("id");
		MenuFormBean mfb = menuFactoryService.queryResMenuByResKy(id);
		
		String resKy = mfb.getResKy() ==  null ? "" : mfb.getResKy(); // 流水号
		String code = mfb.getCode() == null ? "" : mfb.getCode(); // 编号
		String name = mfb.getName() == null ? "" : mfb.getName(); // 名称
		String url = mfb.getUrl() == null ? "" : mfb.getUrl(); // url
		String image = mfb.getImage() == null ? "" : mfb.getImage(); // 图片
		String position = mfb.getPosition() == null ? "" : mfb.getPosition(); // 位置
		String parent = mfb.getParent() ==  null ? "" : mfb.getParent(); // 父节点
		int level = 0; //级数
		MenuFormBean mfbParent = menuFactoryService.queryResMenuByResKy(parent);
		String parentName = mfbParent == null ? "根菜单" : mfbParent.getName(); //父节点名称
		
		String results = "{resKy:\""+resKy+"\",code:\""+code+"\",parentName:\""+parentName+"\","+
				"name:\""+name+"\",url:\""+url+"\",image:\""+image+"\",position:\""+position+"\",parent:\""+parent+"\",level:\""+level+"\"}";
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(results);
		out.flush();
		out.close();
		return null;
	}

	public ModelAndView getNewPosition(HttpServletRequest request,
			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
			throws Exception {
		String parentId = ServletRequestUtils.getStringParameter(request, "parentId");
		int position = 10;
		if(! StringUtil.isEmpty(parentId)){
			position = possMenuService.getNewPosition(Long.valueOf(parentId)) + 10;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(position+"");
		out.close();
		return null;
	}
		
}
