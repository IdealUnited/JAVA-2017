/**
 *  File: ExpressChannelManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.dto.ExpressChannel;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

/**
 * 物流渠道管理
 */
public class ExpressChannelManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String addView;
	private String editView;
	private String initView;
	private String listView;
	private TxncoreChannelClientService txncoreChannelClientService;

	/**
	 * 渠道管理初始页
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
	 * 添加渠道
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, ExpressChannel expressChannel)
			throws Exception {

		String creator = SessionUserHolderUtil.getLoginId();
		expressChannel.setCreator(creator);
		txncoreChannelClientService.createChannel(expressChannel);
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
			HttpServletResponse response, ExpressChannel expressChannel)
			throws Exception {

		List<ExpressChannel> resultList = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		Map resultMap = new HashMap();
		resultMap.put("resultList", resultList);
		return new ModelAndView(listView, resultMap);
	}

	/**
	 * 修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView editInit(HttpServletRequest request,
			HttpServletResponse response, ExpressChannel expressChannel) {

		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);

		return new ModelAndView(editView).addObject("channel",
				expressChannels.get(0));
	}

	/**
	 * 修改渠道
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, ExpressChannel expressChannel)
			throws Exception {

		boolean updateFlag = txncoreChannelClientService
				.updateExpressChannel(expressChannel);

		if (updateFlag) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}

		return null;
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

	public void setTxncoreChannelClientService(
			TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

	public void setEditView(String editView) {
		this.editView = editView;
	}

}
