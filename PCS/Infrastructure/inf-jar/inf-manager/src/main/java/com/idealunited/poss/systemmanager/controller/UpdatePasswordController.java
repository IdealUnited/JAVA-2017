package com.idealunited.poss.systemmanager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.systemmanager.service.IUserService;

/**
 * 修改密码
 * 
 * @Description
 * @project poss-systemmanager
 * @file UpdatePasswordController.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 pay Corporation. All rights reserved. Date
 *          Author Changes 2010-8-10 Volcano.Wu Create
 */
public class UpdatePasswordController extends MultiActionController {

	private String toView;
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public ModelAndView index(HttpServletRequest req,
			HttpServletResponse res) throws ServletRequestBindingException,
			IOException {
		return new ModelAndView(toView);
	}

	public ModelAndView update(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		String currentPassword = req.getParameter("currentPassword");
		String newPassword = req.getParameter("newPassword");
		boolean bl = userService.changePassword(currentPassword,newPassword);
		Map<String,String> model = new HashMap<String,String>();
		if(bl){
			model.put("msg", "修改成功！");
		}else{
			model.put("msg", "密码错误,修改失败,请重试！");
		}
		return new ModelAndView(toView, model);
	}

}
