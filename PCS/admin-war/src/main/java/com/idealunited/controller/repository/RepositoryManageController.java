/**
 *  File: RepositoryManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.repository;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreRepositoryClientService;
import com.idealunited.dto.Repository;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

/**
 * 仓库管理
 */
public class RepositoryManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String addView;
	private String initView;
	private String listView;
	private TxncoreRepositoryClientService txncoreRepositoryClientService;

	/**
	 * 货品信息管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView(initView);
	}

	/**
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addInit(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView(addView);
	}

	/**
	 * 添加SKU
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, Repository repository)
			throws Exception {

		String creator = SessionUserHolderUtil.getLoginId();
		txncoreRepositoryClientService.createRepository(repository);
		response.getWriter().print(1);
		return null;
	}

	/**
	 * 查询货品信息
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, Repository repository)
			throws Exception {

		Page<Repository> resultPage = txncoreRepositoryClientService
				.queryRepository();
		Map resultMap = new HashMap();
		resultMap.put("resultList", resultPage.getResult());
		return new ModelAndView(listView, resultMap);
	}

	public void setAddView(String addView) {
		this.addView = addView;
	}

	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreRepositoryClientService(
			TxncoreRepositoryClientService txncoreRepositoryClientService) {
		this.txncoreRepositoryClientService = txncoreRepositoryClientService;
	}

}
