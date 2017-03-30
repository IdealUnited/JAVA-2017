package com.idealunited.poss.systemmanager.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.poss.security.model.AccessLog;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.security.service.IAccessLogService;
import com.idealunited.poss.security.util.SecutiryRequestUtil;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;

/**
 * 首页
 * @Description 
 * @project 	poss-systemmanager
 * @file 		MainController.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010 pay Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-8-16		Volcano.Wu			Create
 */
public class MainController extends MultiActionController {

	private String topFrame;
	private String topMenu;
	private String menuFrame;
	private String buttonFrame;
	private String bodyFrame;
	
	private IMenuFactoryService menuFactoryService;
	
	private IAccessLogService accessLogService;
	
	public void setMenuFactoryService(IMenuFactoryService menuFactoryService) {
		this.menuFactoryService = menuFactoryService;
	}

	public void setTopFrame(String topFrame) {
		this.topFrame = topFrame;
	}

	public void setTopMenu(String topMenu) {
		this.topMenu = topMenu;
	}

	public void setMenuFrame(String menuFrame) {
		this.menuFrame = menuFrame;
	}

	public void setButtonFrame(String buttonFrame) {
		this.buttonFrame = buttonFrame;
	}

	public void setBodyFrame(String bodyFrame) {
		this.bodyFrame = bodyFrame;
	}
	
	// topFrame
	public ModelAndView topFrame(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication.getPrincipal();
		String userName = sessionUserHolder.getUsername();
		String userKy = sessionUserHolder.getUserKy();
		return new ModelAndView(topFrame).addObject("userName", userName).addObject("userKy", userKy);
	}
	// topMenu
	public ModelAndView topMenu(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		return new ModelAndView(topMenu);
	}
	// menuFrame old 2012-6-4以前
//	public ModelAndView menuFrame(HttpServletRequest req, HttpServletResponse res)
//			throws ServletRequestBindingException, IOException {
//		String htmlMenu = menuFactoryService.getHtmlMenu();
//		return new ModelAndView(menuFrame).addObject("htmlMenu", htmlMenu);
//	}
	// buttonFrame
	public ModelAndView buttonFrame(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		return new ModelAndView(buttonFrame);
	}
	// bodyFrame
	public ModelAndView bodyFrame(HttpServletRequest req, HttpServletResponse res)
			throws ServletRequestBindingException, IOException {
		return new ModelAndView(bodyFrame);
	}
	
	// menuFrame _new  DDR 改造 2012-6-4以后
	public ModelAndView menuFrame(HttpServletRequest req, HttpServletResponse res)
				throws ServletRequestBindingException, IOException {
		
			List<ResMenu> menuList = menuFactoryService.queryForCurrMenuTree();
			return new ModelAndView(menuFrame).addObject("menuList", menuList);
		}
	
	/**
	 * 用户注销日志
	 * @param req
	 * @param res
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView logout(HttpServletRequest req,
			HttpServletResponse res) throws ServletRequestBindingException,
			IOException {
		HttpSession session = (req.getSession());
		String ip = SecutiryRequestUtil.getIp(req);
		final AccessLog log = new AccessLog();
		log.setActionUrl("main.do?method=logout");
		String loginUser = String.valueOf(session
				.getAttribute("SPRING_SECURITY_LAST_USERNAME"));
		if (loginUser != null) {
			log.setLoginUser(loginUser);
		}
		log.setUrlMethod(req.getMethod().toUpperCase());
		log.setUserIp(ip);
		log.setCreationDate(new Date());
		accessLogService.createAccessLog(log);
		return new ModelAndView("redirect:j_spring_security_logout");
	}
	
	public void setAccessLogService(IAccessLogService accessLogService) {
		this.accessLogService = accessLogService;
	}
	
}
