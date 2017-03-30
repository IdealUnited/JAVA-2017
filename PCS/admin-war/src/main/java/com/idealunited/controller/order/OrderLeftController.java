/**
 *  File: OrderManageController.java
 *  Date: 2016年5月26日
 *  Author: chaoyue 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.controller.order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.idealunited.client.TxncoreChannelClientService;
import com.idealunited.client.TxncoreOrderClientService;
import com.idealunited.dto.TradeOrder;
import com.idealunited.inf.comm.PageUtils;
import com.idealunited.inf.dao.Page;
import com.idealunited.util.DateUtil;
import com.idealunited.util.StringUtil;

/**
 * 结余订单处理
 */
public class OrderLeftController extends MultiActionController {

	private final Log logger = LogFactory.getLog(getClass());
	private String initView;
	private String listView;
	private static final String KEY_BEGIN_TIME = "beginTime";
	private static final String KEY_END_TIME = "endTime";
	
	private TxncoreOrderClientService txncoreOrderClientService;
	private TxncoreChannelClientService txncoreChannelClientService;

	/**
	 * 产品库存管理初始页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startTime = DateUtil.skipDateTimeStr( new Date( ), -3 );
		String endTime = DateUtil.skipDateTimeStr( new Date( ), 0 );

		return new ModelAndView(initView).addObject( KEY_BEGIN_TIME, startTime ).addObject(
				KEY_END_TIME, endTime );
	}
	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @param productStock
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, OrderCriteria orderCriteria)
			throws Exception {
		Page<TradeOrder> page = PageUtils.getPage(request); // 分页
		String channelOrderItemId=StringUtil.null2String(request.getParameter("channelorderItemId"));
		String orderId=StringUtil.null2String(request.getParameter("orderId"));
		String beginTime=StringUtil.null2String(request.getParameter("beginTime"));
		String endTime=StringUtil.null2String(request.getParameter("endTime"));
		String channelOrderstatus="0";//创建
		Map queryMap =new HashMap();
		queryMap.put("orderId", orderId);
		queryMap.put("channelOrderItemId", channelOrderItemId);
		queryMap.put("channelStartTime", beginTime);
		queryMap.put("channelEndTime", endTime);
		queryMap.put("channelOrderstatus", channelOrderstatus);
		Page<Map> resultPage = txncoreOrderClientService.queryOrderItemInfo(queryMap, page);
		Map resultMap = new HashMap();
		resultMap.put("page", resultPage);
		return new ModelAndView(listView, resultMap);
	}
	
	
	public void setInitView(String initView) {
		this.initView = initView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public void setTxncoreOrderClientService(TxncoreOrderClientService txncoreOrderClientService) {
		this.txncoreOrderClientService = txncoreOrderClientService;
	}

	public void setTxncoreChannelClientService(TxncoreChannelClientService txncoreChannelClientService) {
		this.txncoreChannelClientService = txncoreChannelClientService;
	}

}
