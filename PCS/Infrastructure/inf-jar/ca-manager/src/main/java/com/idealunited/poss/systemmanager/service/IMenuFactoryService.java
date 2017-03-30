package com.idealunited.poss.systemmanager.service;

import java.util.List;

import com.idealunited.poss.systemmanager.formbean.Menu;
import com.idealunited.poss.systemmanager.formbean.MenuFormBean;
import com.idealunited.poss.systemmanager.model.ResMenu;

/**
 * 
 * @Description 
 * @project 	poss-systemmanager
 * @file 		IMenuFactory.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-28		Volcano.Wu			Create
 */
public interface IMenuFactoryService {
	
	public List<Menu> getHeadMenu();
	
	public List<Menu> getSonMenuList(String parentCode);
	
	public String getxtreeScriptToStr();
	
	public MenuFormBean queryResMenuByResKy(String resKy);
	
	public List<MenuFormBean> queryMenuByPerentKy(String perentKy);
	
	public String getDtreeScriptStr();
	
	public String queryResKyByRoleKy(String roleKy);
	
	public String getHtmlMenu();
	
	/**
	 * 查询整个树，用于管理
	 * @return List<ResMenu>
	 */
	public List<ResMenu> queryMenuTree();
	
	/**
	 * 查询当前用户的菜单树
	 * @param loginId
	 * @return List<ResMenu>
	 */
	public List<ResMenu> queryForCurrMenuTree();
	
	/**
	 * 查询当前被选中的role对应的菜单ID
	 * @param id
	 * @return List<Long> 选中的id列表
	 */
	public List<Long> queryCheckedMenuIdByRoleId(long id);
	
	

}
