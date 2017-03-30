package com.idealunited.poss.systemmanager.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.systemmanager.formbean.RoleFormBean;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.model.Role;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;
import com.idealunited.poss.systemmanager.service.IRoleService;

/**
 * 角色管理
 * @Description 
 * @project 	poss-systemmanager
 * @file 		RoleAdminController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 	2006-2012 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-4		Volcano.Wu			Create
 */
public class RoleAdminController extends MultiActionController {
	
	private Log log = LogFactory.getLog(this.getClass());
	private IRoleService roleService;
	private IMenuFactoryService menuFactoryService;
	private String toView;
	private String listView;
	private String addView;
	
	
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setMenuFactoryService(IMenuFactoryService menuFactoryService) {
		this.menuFactoryService = menuFactoryService;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	//查询页
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(toView);
	}
	
	//搜索
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Page<Role>> model = new HashMap<String,Page<Role>>();
		String roleName = request.getParameter("roleName");
		Map<String,String> map = new HashMap<String,String>();
		map.put("roleName", roleName);
		
		Page<Role> page = PageUtils.getPage(request);
    	page = roleService.search(page,map);   	
    	model.put("page",page);
    	
		return new ModelAndView(listView,model);
	}
	
	//初始化页 
	/**
	 * 2012-07-06
	 * DDR优化版本
	 * 新增或修改权限配置模块
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleKy = ServletRequestUtils.getStringParameter(request,"roleKy","");
		Role role = null;
		Map<String, Object> model =  new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(roleKy)){
			role = roleService.queryRoleByRoleKy(roleKy);
			List<Long> checkIds =  menuFactoryService.queryCheckedMenuIdByRoleId(Long.parseLong(roleKy));
			model.put("role", role);
			model.put("checkIds", checkIds);
			String checkIdsStr = "";
			for(long id:checkIds){
				checkIdsStr+=","+id;
			}
			model.put("checkIdsStr", checkIdsStr);
		}
		List<ResMenu> treeMenuList = menuFactoryService.queryMenuTree();
		model.put("treeMenuList", treeMenuList);
		return new ModelAndView(addView,model);
	}
	
	//角色新增
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response,RoleFormBean roleFormBean) throws Exception {
		
		String roleKy = request.getParameter("roleKy");
		long[] menuIds = ServletRequestUtils.getLongParameters(request,"menuId");
		boolean status = roleService.insertBatch(roleKy,roleFormBean,menuIds);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().print(status?"S":"操作失败");
		
		return null;
	}
	//角色新增
	public ModelAndView isExsit(HttpServletRequest request,HttpServletResponse response,RoleFormBean roleFormBean) throws Exception {
		Map map = new HashMap();
		map.put("roleName", roleFormBean.getRoleName());
		List list = roleService.queryRole(map);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (list != null && !list.isEmpty()) {
			response.getWriter().print(1);
		}else {
			response.getWriter().print(0);
		}
		return null;
	}
	
	//删除角色
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String roleKy = request.getParameter("id");
		PrintWriter out = response.getWriter();
		if ((Boolean)roleService.deleteRole(roleKy))
			out.print("success");
		else
			out.print("error");
		return null;
	}
}
