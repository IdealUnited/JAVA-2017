package com.idealunited.poss.systemmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreRepositoryClientService;
import com.idealunited.dto.Repository;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.security.util.SessionUserHolderUtil;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.formbean.UserSearchFormBean;
import com.idealunited.poss.systemmanager.service.IUserService;

/**
 * 用户管理
 * 
 * @Description
 * @project poss-systemmanager
 * @file UserController.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved. Date
 *          Author Changes 2010-7-27 Volcano.Wu Create
 */
public class UserController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String viewName;
	private String toView;
	private String addView;
	private String editView;
	private TxncoreRepositoryClientService txncoreRepositoryClientService;
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setTxncoreRepositoryClientService(
			TxncoreRepositoryClientService txncoreRepositoryClientService) {
		this.txncoreRepositoryClientService = txncoreRepositoryClientService;
	}

	// 依赖注入viewName的参数,例如一个JSP文件，作为展示model的视图
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public void setEditView(String editView) {
		this.editView = editView;
	}

	// userInit
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(toView).addObject("sessionUser",
				SessionUserHolderUtil.getSessionUserHolder());
	}

	// 用户列表
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, UserSearchFormBean userSearchFormBean)
			throws Exception {

		Map<String, Page<UserFormBean>> model = new HashMap<String, Page<UserFormBean>>();

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

		Page<UserFormBean> page = PageUtils.getPage(request); // 分页
		page = userService.search(page, searchMap);

		// List<UserFormBean> users = userService.queryAll(searchMap);

		model.put("page", page);
		return new ModelAndView(viewName, model);
	}

	// 初始化新增
	public ModelAndView init(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		Map model = new HashMap();
		model.put("dataList", "新增数据...");
		return new ModelAndView(addView, model);
	}

	public ModelAndView insert(HttpServletRequest request,
			HttpServletResponse response, UserFormBean user)
			throws ServletRequestBindingException, IOException {
		Map model = new HashMap();
		model.put("dataList", "新增数据...");
		return new ModelAndView(viewName, model);
	}

	public ModelAndView ajaxUpdate(HttpServletRequest req,
			HttpServletResponse res, UserFormBean user)
			throws ServletRequestBindingException, IOException {
		try {

			PrintWriter out = res.getWriter();
			if ((Boolean) userService.updateUser(user))
				out.print("success");
			else
				out.print("error");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			UserFormBean user) throws ServletRequestBindingException,
			IOException {
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			e.getMessage();
		}
		return new ModelAndView(toView);
	}

	public ModelAndView updateInit(HttpServletRequest req,
			HttpServletResponse res) throws ServletRequestBindingException,
			IOException {

		String id = req.getParameter("id");
		UserFormBean user = userService.queryUserByKy(id);

		Map model = userService.queryAllDutyAndOrg();
		model.put("user", user);

		List<Repository> repositorys = txncoreRepositoryClientService
				.queryRepository().getResult();
		model.put("repositorys", repositorys);
		return new ModelAndView(editView, model);
	}

	public ModelAndView delete(HttpServletRequest req,
			HttpServletResponse response)
			throws ServletRequestBindingException, IOException {

		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String id = req.getParameter("id");
		try {
			PrintWriter out = response.getWriter();
			SessionUserHolder loginUser = SessionUserHolderUtil
					.getSessionUserHolder();
			if (loginUser != null && id.equals(loginUser.getUserKy())) {
				out.print("不能删除自己");
			} else if ((Boolean) userService.deleteUser(id))
				out.print("success");
			else
				out.print("删除出现问题");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
