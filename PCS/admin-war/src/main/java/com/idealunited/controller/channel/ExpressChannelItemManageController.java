/**
 *  File: ExpressChannelItemManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.channel;

import java.util.ArrayList;
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
import com.idealunited.dto.ExpressChannelItem;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

/**
 * 物流渠道明细管理
 */
public class ExpressChannelItemManageController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String addView;
	private String updateView;
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
			HttpServletResponse response, ExpressChannelItem expressChannelItem)
			throws Exception {
		ExpressChannel expressChannel = new ExpressChannel();
		expressChannel.setId(expressChannelItem.getChannelId());
		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		return new ModelAndView(initView).addObject("channel",
				expressChannels.get(0));
	}

	/**
	 * 添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addInit(HttpServletRequest request,
			HttpServletResponse response, ExpressChannelItem expressChannelItem) {

		ExpressChannel expressChannel = new ExpressChannel();
		expressChannel.setId(expressChannelItem.getChannelId());
		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		return new ModelAndView(addView).addObject("channel",
				expressChannels.get(0));
	}

	/**
	 * 添加渠道
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, ExpressChannelItem expressChannelItem)
			throws Exception {

		List<ExpressChannelItem> expressChannelItems = new ArrayList<ExpressChannelItem>();
		String creator = SessionUserHolderUtil.getLoginId();
		expressChannelItem.setCreator(creator);
		expressChannelItems.add(expressChannelItem);
		boolean addFlag = txncoreChannelClientService
				.createChannelItem(expressChannelItems);
		if (addFlag) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}

		return null;
	}

	/**
	 * 跳转更新页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateInit(HttpServletRequest request,
			HttpServletResponse response, ExpressChannelItem expressChannelItem) {

		Page<ExpressChannelItem> page = txncoreChannelClientService
				.queryExpressChannelItem(expressChannelItem);

		ExpressChannel expressChannel = new ExpressChannel();
		expressChannel.setId(expressChannelItem.getChannelId());
		List<ExpressChannel> expressChannels = txncoreChannelClientService
				.queryExpressChannel(expressChannel);
		ExpressChannelItem item = page.getResult().get(0);
		return new ModelAndView(updateView).addObject("channel",
				expressChannels.get(0)).addObject("item", item);
	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, ExpressChannelItem expressChannelItem)
			throws Exception {

		List<ExpressChannelItem> expressChannelItems = new ArrayList<ExpressChannelItem>();
		String creator = SessionUserHolderUtil.getLoginId();
		expressChannelItem.setCreator(creator);
		expressChannelItems.add(expressChannelItem);
		boolean updateFlag = txncoreChannelClientService
				.updateExpressChannel(expressChannelItems);
		if (updateFlag) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}

		return null;
	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, ExpressChannelItem expressChannelItem)
			throws Exception {

		Page<ExpressChannelItem> resultPage = txncoreChannelClientService
				.queryExpressChannelItem(expressChannelItem);
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

	public void setTxncoreChannelClientService(
			TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

	public void setUpdateView(String updateView) {
		this.updateView = updateView;
	}

}
