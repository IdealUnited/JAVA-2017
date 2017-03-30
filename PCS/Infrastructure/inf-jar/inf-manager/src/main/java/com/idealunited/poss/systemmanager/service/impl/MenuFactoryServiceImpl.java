package com.idealunited.poss.systemmanager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.systemmanager.formbean.Menu;
import com.idealunited.poss.systemmanager.formbean.MenuFormBean;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;

/**
 * 菜单树组织处理管理Service
 * 
 * @Description
 * @project poss-systemmanager
 * @file MenuFactoryServiceImpl.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved. Date
 *          Author Changes 2010-8-4 Volcano.Wu Create 2012-7-4 DDR Update
 */
public class MenuFactoryServiceImpl implements IMenuFactoryService {

	private BaseDAO daoService;

	private List<Menu> menuList;

	private List<Menu> headMenu;

	private List<Menu> leftMenu;

	public List<Menu> getSonMenuList(String parentCode) {
		menuList = new ArrayList<Menu>();
		List<Menu> lmenu = getLeftMenu(parentCode);
		for (Menu menu : lmenu) {
			List<Menu> sonList = getLeftMenu(menu.getCode());
			menu.setSonMenuList(sonList);
			menuList.add(menu);
		}
		return menuList;
	}

	/**
	 * get LeftMenu
	 * 
	 * @param parentCode
	 * @return List<Menu>
	 */
	public List<Menu> getLeftMenu(String parentCode) {
		leftMenu = new ArrayList<Menu>();

		List<MenuFormBean> menuBeanList = queryMenuByPerentCode(parentCode);
		for (MenuFormBean mb : menuBeanList) {
			Menu menu = new Menu();
			menu.setCode(mb.getResKy().toString());
			menu.setText(mb.getName());
			menu.setUrl(mb.getUrl());
			menu.setImgUrl(mb.getImage());
			leftMenu.add(menu);
		}
		return leftMenu;
	}

	/**
	 * get HeadMenu
	 */
	public List<Menu> getHeadMenu() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication
				.getPrincipal();
		if (sessionUserHolder.isSuperManagerOfA()) {
			List<MenuFormBean> mfbList = queryAllRootMenu();
			headMenu = new ArrayList<Menu>();
			for (MenuFormBean mfb : mfbList) {
				Menu menu = new Menu();
				menu.setCode(mfb.getResKy().toString());
				menu.setText(mfb.getName());
				menu.setUrl(mfb.getUrl());
				menu.setImgUrl(mfb.getImage());
				headMenu.add(menu);
			}
		} else {
			List<String> currentUserRoleKy = getCurrentUserRoleList();
			headMenu = new ArrayList<Menu>();

			List<MenuFormBean> menuBeanList = queryParentMenuByRoleKy(currentUserRoleKy);
			for (MenuFormBean mb : menuBeanList) {
				Menu menu = new Menu();
				menu.setCode(mb.getResKy().toString());
				menu.setText(mb.getName());
				menu.setUrl(mb.getUrl());
				menu.setImgUrl(mb.getImage());
				headMenu.add(menu);
			}
		}
		return headMenu;
	}

	/**
	 * get HtmlMenu
	 * 
	 * @return String
	 */
	public String getHtmlMenu() {
		StringBuffer htmlMenu = new StringBuffer();

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication
				.getPrincipal();
		if (sessionUserHolder.isSuperManagerOfA()) {
			List<MenuFormBean> menuList = getMenuTree();
			Map<String, String> map = new HashMap<String, String>();
			map.put("isSuperManager", "isSuperManager");
			processHtmlMenu(menuList, htmlMenu, map);
		} else {
			Map<String, String> map = getCurrentUserResKys();
			List<MenuFormBean> menuList = getMenuTreeByRoleKy();
			processHtmlMenu(menuList, htmlMenu, map);
		}
		return htmlMenu.toString();
	}

	/**
	 * 组织HtmlMenu
	 * 
	 * @param menuList
	 * @param htmlMenu
	 */
	private void processHtmlMenu(List<MenuFormBean> menuList,
			StringBuffer htmlMenu, Map<String, String> map) {

		int n = menuList.size();
		String isSuperManager = map.get("isSuperManager");
		int index = 0;
		for (MenuFormBean oneLevel : menuList) {
			index++;
			List<MenuFormBean> twoLevels = oneLevel.getChilds();
			if (twoLevels != null && twoLevels.size() > 0) {
				String oneLevelName = oneLevel.getName(); // 一级菜单名

				htmlMenu.append("<div style=\"overflow-x:hidden;\" title=\""
						+ oneLevelName + "\" selected=\"false\">");
				htmlMenu.append("<ul id=\"tree"
						+ index
						+ "\" name=\"trees\" class=\"easyui-tree\" animate=\"true\" style=\"padding:1px;\">");
				if (twoLevels != null && twoLevels.size() > 0) {
					for (MenuFormBean twoLevel : twoLevels) {
						String twoLevelResKy = twoLevel.getResKy();
						String twoLevelKy = map.get(twoLevelResKy);
						if (StringUtils.isNotEmpty(twoLevelKy)
								|| "isSuperManager".equals(isSuperManager)) {
							String twoLevelName = twoLevel.getName(); // 二级菜单名

							htmlMenu.append("<li state=\"closed\">");
							htmlMenu.append("<span>" + twoLevelName + "</span>");
							htmlMenu.append("<ul state=\"closed\">");

							List<MenuFormBean> threeLevels = twoLevel
									.getChilds();
							if (threeLevels != null && threeLevels.size() > 0) {
								for (MenuFormBean threeLevel : threeLevels) {
									String threeLevelResKy = threeLevel
											.getResKy();
									String threeLevelKy = map
											.get(threeLevelResKy);
									if (StringUtils.isNotEmpty(threeLevelKy)
											|| "isSuperManager"
													.equals(isSuperManager)) {
										String threeLevelName = threeLevel
												.getName(); // 三级菜单名
										String threeLevelUrl = threeLevel
												.getUrl(); // 三级菜单Url
										htmlMenu.append("<li>");
										htmlMenu.append("<a onclick=\"open1('"
												+ threeLevelName
												+ "','"
												+ threeLevelUrl
												+ "')\" name=\"leftMenu\" title=\""
												+ threeLevelName + "\" rel="
												+ threeLevelUrl + ">"
												+ threeLevelName + "</a>");
										htmlMenu.append("</li>");
									}
								}
							}
							htmlMenu.append("</ul>");
							htmlMenu.append("</li>");
						}
					}
				}
				htmlMenu.append("</div>");
			}
		}
	}

	/**
	 * get DtreeMenu
	 * 
	 * @return String
	 */
	public String getDtreeScriptStr() {

		/*
		 * allAuthMap = new HashMap<String, String>(); allAuths =
		 * authorityManager.getAll(); for (Authority allAuth : allAuths) {
		 * allAuthMap.put(allAuth.getName(), allAuth.getId().toString()); }
		 * 
		 * Element root = loadMenuDocument().getRootElement();
		 */
		StringBuffer scriptStr = new StringBuffer();
		scriptStr.append("d.add(" + 0 + ",-1," + "'系统菜单'" + ")\n");
		List<MenuFormBean> menus = getMenuTree();
		/*
		 * if (true) scriptStr.append("d.add(" + 0 + ",-1," + "''," + "'菜单权限',"
		 * + "'','','','')\n"); else scriptStr.append("d.add(" + 0 + ",-1," +
		 * "''," + "'系统菜单'," + "'','','','')\n");
		 */

		ProcessDtree(menus, scriptStr);
		return scriptStr.toString();
	}

	/**
	 * 组织DtreeMenu
	 * 
	 * @param menuList
	 * @param scriptStr
	 */
	private void ProcessDtree(List<MenuFormBean> menuList,
			StringBuffer scriptStr) {

		for (MenuFormBean mfb : menuList) {
			String id = mfb.getResKy(); // 编号
			String text = mfb.getName(); // 资源名
			String url = "";
			String position = mfb.getPosition() == null ? "" : mfb
					.getPosition(); // 排序
			String parent = mfb.getParent(); // 父节点

			scriptStr.append("d.add(" + "'" + id + "',");
			if (StringUtils.isEmpty(parent))
				scriptStr.append("0,");
			else {
				scriptStr.append("'" + parent + "',");
			}
			scriptStr.append("'" + text + "',");
			scriptStr.append("'" + url + "',");
			scriptStr.append("'" + text + "',");
			scriptStr.append("'mainFrame')\n");
			List<MenuFormBean> childs = mfb.getChilds();
			if (childs != null)
				ProcessDtree(mfb.getChilds(), scriptStr);
		}
	}

	/**
	 * 处理父节点的子节点
	 * 
	 * @param mfb
	 * @param xtreeStr
	 * @param s
	 */
	private void processXtree(MenuFormBean mfb, StringBuffer xtreeStr, String s) {
		for (MenuFormBean child : mfb.getChilds()) {
			String c = "ti" + child.getResKy();
			xtreeStr.append("var " + c + " = new WebFXTreeItem('"
					+ child.getResKy() + "','" + child.getName()
					+ "','javascript:show();'," + s + ");\n");
			if (child.getChilds() != null && !child.getChilds().isEmpty()) {
				processXtree(child, xtreeStr, c);
			}
		}

	}

	/**
	 * 组织xtree菜单树
	 * 
	 * @param list
	 * @return
	 */
	public String getxtreeScriptToStr() {

		List<MenuFormBean> menus = getMenuTree();
		StringBuffer xtreeStr = new StringBuffer();
		for (MenuFormBean mfb : menus) {
			String s = "ti" + mfb.getResKy();
			if (mfb.getParent() == null || "0".equals(mfb.getParent())) {
				xtreeStr.append("var " + s + " = new WebFXTreeItem('"
						+ mfb.getResKy() + "','" + mfb.getName()
						+ "','javascript:show();',tree);\n");
				processXtree(mfb, xtreeStr, s);
			}
		}
		return xtreeStr.toString();
	}

	private List<MenuFormBean> getMenuTreeByRoleKy() {

		List<String> currentUserRoleKy = getCurrentUserRoleList();
		if (CollectionUtils.isEmpty(currentUserRoleKy)) {
			return new ArrayList<MenuFormBean>();
		}
		// 查询出根节点菜单
		List<MenuFormBean> rootMenus = queryParentMenuByRoleKy(currentUserRoleKy);
		for (MenuFormBean rootMenu : rootMenus) {
			// 查询出二级菜单
			List<MenuFormBean> twoMenus = queryMenuByPerentKy(rootMenu
					.getResKy());
			for (MenuFormBean twoMenu : twoMenus) {
				// 查询出二级菜单的父菜单
				MenuFormBean twoMenuPerent = queryResMenuByResKy(twoMenu
						.getParent());
				// 查询出三级菜单
				List<MenuFormBean> threeMenus = queryMenuByPerentKy(twoMenu
						.getResKy());
				for (MenuFormBean threeMenu : threeMenus) {
					// 查询出三级菜单的父菜单
					MenuFormBean threeMenuPerent = queryResMenuByResKy(threeMenu
							.getParent());
					// 查询出四级菜单
					List<MenuFormBean> fourMenus = queryMenuByPerentKy(threeMenu
							.getResKy());

					threeMenu.setChilds(fourMenus);
					threeMenu.setParentMenu(threeMenuPerent);
				}
				twoMenu.setChilds(threeMenus);
				twoMenu.setParentMenu(twoMenuPerent);
			}
			rootMenu.setChilds(twoMenus);
		}
		return rootMenus;
	}

	/**
	 * get MenuTree
	 * 
	 * @return List<MenuFormBean>
	 */
	private List<MenuFormBean> getMenuTree() {
		// 查询出根节点菜单
		List<MenuFormBean> rootMenus = queryAllRootMenu();
		for (MenuFormBean rootMenu : rootMenus) {
			// 查询出二级菜单
			List<MenuFormBean> twoMenus = queryMenuByPerentKy(rootMenu
					.getResKy());
			for (MenuFormBean twoMenu : twoMenus) {
				// 查询出二级菜单的父菜单
				MenuFormBean twoMenuPerent = queryResMenuByResKy(twoMenu
						.getParent());
				// 查询出三级菜单
				List<MenuFormBean> threeMenus = queryMenuByPerentKy(twoMenu
						.getResKy());
				for (MenuFormBean threeMenu : threeMenus) {
					// 查询出三级菜单的父菜单
					MenuFormBean threeMenuPerent = queryResMenuByResKy(threeMenu
							.getParent());
					// 查询出四级菜单
					List<MenuFormBean> fourMenus = queryMenuByPerentKy(threeMenu
							.getResKy());

					threeMenu.setChilds(fourMenus);
					threeMenu.setParentMenu(threeMenuPerent);
				}
				twoMenu.setChilds(threeMenus);
				twoMenu.setParentMenu(twoMenuPerent);
			}
			rootMenu.setChilds(twoMenus);
		}
		return rootMenus;
	}

	/**
	 * 查询出所有根菜单节点
	 * 
	 * @return List<MenuFormBean>
	 */
	private List<MenuFormBean> queryAllRootMenu() {
		return daoService.findByQuery("menu.queryRootMenu", null);
	}

	/**
	 * 根据resKy查询出ResMenu
	 * 
	 * @param resKy
	 * @return MenuFormBean
	 */
	public MenuFormBean queryResMenuByResKy(String resKy) {
		return (MenuFormBean) daoService.findObjectByCriteria(
				"menu.queryResMenuByResKy", resKy);
	}

	/**
	 * 根据角色编号查询出角色对应的资源集合
	 * 
	 * @param roleKy
	 * @return
	 */
	public String queryResKyByRoleKy(String roleKy) {
		List<String> resKyList = daoService.findByQuery(
				"menu.queryResKyByRoleKy", roleKy);
		StringBuffer resKyStr = new StringBuffer();
		if (resKyList != null && resKyList.size() > 0) {
			for (String resKy : resKyList) {
				resKyStr.append(resKy);
				resKyStr.append(",");
			}
		}
		return resKyStr.toString();
	}

	/**
	 * 根椐父菜单Ky查询出子菜单
	 * 
	 * @param perentCode
	 * @return
	 */
	public List<MenuFormBean> queryMenuByPerentKy(String perentKy) {
		return daoService.findByQuery("menu.queryMenuByPerentKy", perentKy);
	}

	/**
	 * 根椐角色编号查询出顶级菜单
	 * 
	 * @param userRoleCodeList
	 * @return
	 */
	private List<MenuFormBean> queryParentMenuByRoleKy(
			List<String> userRoleKyList) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("userRoleKyList", userRoleKyList);
		return daoService.findByQuery("menu.queryParentMenuByRoleKy", map);
	}

	/**
	 * 查询出角色所有的资源编号
	 * 
	 * @param userRoleCodeList
	 * @return
	 */
	private List<MenuFormBean> queryMenuResKyByRoleKy(
			List<String> userRoleKyList) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("userRoleKyList", userRoleKyList);
		return daoService.findByQuery("menu.queryMenuResKyByRoleKy", map);
	}

	/**
	 * 获取当前登录用户的所有资源ky
	 * 
	 * @return
	 */
	private Map<String, String> getCurrentUserResKys() {
		Map<String, String> map = new HashMap<String, String>();
		List<String> userRoleList = getCurrentUserRoleList();
		List<MenuFormBean> mfbList = new ArrayList<MenuFormBean>();
		if (userRoleList != null && userRoleList.size() > 0) {
			mfbList = queryMenuResKyByRoleKy(userRoleList);
		}
		for (MenuFormBean mfb : mfbList) {
			String resKy = mfb != null ? mfb.getResKy() : "";
			map.put(resKy, resKy);
		}
		return map;
	}

	/**
	 * 根椐父菜单编号查询出子菜单
	 * 
	 * @param perentCode
	 * @return
	 */
	private List<MenuFormBean> queryMenuByPerentCode(String perentCode) {
		return daoService.findByQuery("menu.queryMenuByPerentCode", perentCode);
	}

	// 获取当前用户RoleCodeList
	private List<String> getCurrentUserRoleList() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication
				.getPrincipal();
		GrantedAuthority[] arrayAuths = sessionUserHolder.getAuthorities();
		List<String> roleKyList = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : arrayAuths) {
			String roleKy = grantedAuthority.getAuthority();
			roleKyList.add(roleKy);
		}
		return roleKyList;
	}

	@Override
	public List<ResMenu> queryMenuTree() {
		List<ResMenu> list = daoService.findByQuery("menu.queryForMenuTree",
				null);
		return list;
	}

	@Override
	public List<ResMenu> queryForCurrMenuTree() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication
				.getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginId", sessionUserHolder.getUsername());
		map.put("isSuperAdmin", (sessionUserHolder.isSuperManagerOfA()) ? 1 : 0);
		List<ResMenu> list = daoService.findByQuery(
				"menu.queryForCurrMenuTree", map);
		return list;
	}

	@Override
	public List<Long> queryCheckedMenuIdByRoleId(long id) {

		return daoService.findByQuery("menu.getResMenuIdByRoleKy", id);
	}

	public void setDaoService(BaseDAO daoService) {
		this.daoService = daoService;
	}

}
