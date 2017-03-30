package com.idealunited.poss.systemmanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 登录成功后 跳转到首页
 * @Description 
 * @project 	poss-systemmanager
 * @file 		ForwordMainController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-2		Volcano.Wu			Create
 */
public class ForwordMainController extends MultiActionController {

	private String mainView;

	public void setMainView(String mainView) {
		this.mainView = mainView;
	}

	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView(mainView);
	}
}
