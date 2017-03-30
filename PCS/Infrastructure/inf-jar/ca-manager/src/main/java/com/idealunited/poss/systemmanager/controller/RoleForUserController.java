package com.idealunited.poss.systemmanager.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.systemmanager.formbean.RoleFormBean;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.formbean.UserSearchFormBean;
import com.idealunited.poss.systemmanager.model.Role;
import com.idealunited.poss.systemmanager.service.IRoleService;
import com.idealunited.poss.systemmanager.service.IUserService;

/**
 * 用户角色分配
 * 
 * @Description
 * @project poss-systemmanager
 * @file MenuForUserController.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved. Date
 *          Author Changes 2010-8-4 Volcano.Wu Create
 */
public class RoleForUserController extends MultiActionController {
	

	private Log log = LogFactory.getLog(RoleJoinUserController.class);
	private IUserService userService;
	private IRoleService roleService;
	private String toView;
	private String listView;
	private String forUser;

	
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}
	
	public void setForUser(String forUser) {
		this.forUser = forUser;
	}

	// 用户列表
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
			throws Exception {

		Map<String, List<?>> model = new HashMap<String, List<?>>();

		String userId = userSearchFormBean.getUserId();
		String userName = userSearchFormBean.getUserName();
		String status = userSearchFormBean.getStatus();

		Map<String, String> searchMap = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(userId))
			searchMap.put("userId", userId);
		if (StringUtils.isNotEmpty(userName))
			searchMap.put("userName", userName);
		if (StringUtils.isNotEmpty(status) && !"2".equals(status))
			searchMap.put("status", status);

		List<RoleFormBean> roles = roleService.allRole();
		List<UserFormBean> users = userService.queryAll(searchMap);
		
		model.put("users", users);
		model.put("roles", roles);
		return new ModelAndView(toView, model);
	}
	
	//用户列表 
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response,UserSearchFormBean userSearchFormBean) throws Exception {
		
		Map<String,Page<UserFormBean>> model = new HashMap<String,Page<UserFormBean>>();
		
	
		String userId = userSearchFormBean.getUserId();
		String userName  = userSearchFormBean.getUserName();
		String status  = userSearchFormBean.getStatus();
		
		Map<String,String> searchMap = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(userId))
			searchMap.put("userId", userId);
		if(StringUtils.isNotEmpty(userName))
			searchMap.put("userName", userName);
		if(StringUtils.isNotEmpty(status) && !"2".equals(status))
			searchMap.put("status",status);
		
		
		Page<UserFormBean> page = PageUtils.getPage(request);
		page = userService.search(page, searchMap);
		
		//List<UserFormBean> users = userService.queryAll(searchMap);
		
		model.put("page", page);
		return new ModelAndView(listView,model);
	}
	
	// 用户角色分配
	public ModelAndView roleForUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String userId = request.getParameter("userId");
    	Map<String, Object> dataMap = new Hashtable<String, Object>();
    	
    	UserFormBean userFormBean = userService.queryUserByKy(userId);
    	String userName = userFormBean != null ? userFormBean.getUserName() : "";
    	
    		
		List<Role> roleList = roleService.queryRoleOfUser(userId);
		List<Role> roleAllList = roleService.findAllRole();
		List<Role> roleNoList = new ArrayList<Role>();
		
		for(int i=0;i<roleAllList.size();i++){
			boolean sign = true;
			Role roleAll = roleAllList.get(i);
			for(int j=0;j<roleList.size();j++){
				Role role = roleList.get(j);
				if(roleAll.getRoleKy().toString().equals(role.getRoleKy().toString())){
					sign = false;
				}
			}
			if(sign){
				roleNoList.add(roleAll);
			}
		}
		    		   		
		dataMap.put("roleList", roleList);
		dataMap.put("roleNoList", roleNoList);
		dataMap.put("userId", userId);
		dataMap.put("userName", userName);
    	
		return new ModelAndView(forUser,dataMap);
	}
	
	
	// 单个选中
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String id = request.getParameter("id");
		
		
		List<Role> roleList = roleService.queryRoleOfUser(id);
		StringBuffer results = new StringBuffer();
		for (Role role : roleList) {
			String roleKy = role.getRoleKy() != null ? role.getRoleKy().toString() : "";
			String roleName = role.getRoleName();
			results.append("<option value='"+ roleKy +"'>"+roleName+"</option>");  
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(results.toString());
		out.flush();
		out.close();
		
		return null;
	}
	
	// 可选
	public ModelAndView optional(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String id = request.getParameter("id");
		List<Role> roleList = roleService.queryRoleOfUser(id);
		List<RoleFormBean> roles = roleService.allRole();
		StringBuffer results = new StringBuffer();
	
		for (RoleFormBean roleFormBean : roles) {
			for (Role role : roleList) {
				String roleFormKy = roleFormBean.getRoleKy();
				String roleKy = role.getRoleKy() != null ? role.getRoleKy().toString() : "";
				if(roleFormKy != null && roleFormKy.equals(roleKy)){
					roles.remove(roleFormBean);
				}
			}
		}
		
		for (RoleFormBean roleFormBean : roles) {
			String roleKy = roleFormBean.getRoleKy() != null ? roleFormBean.getRoleKy().toString() : "";
			String roleName = roleFormBean.getRoleName();
			results.append("<option value='"+ roleKy +"'>"+roleName+"</option>");  
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(results.toString());
		out.flush();
		out.close();
		
		return null;
	}

}
